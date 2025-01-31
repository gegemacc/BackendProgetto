package my.ecommerce.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import my.ecommerce.dtos.OrderDTO;
import my.ecommerce.entities.*;
import my.ecommerce.enums.ProductStatus;
import my.ecommerce.exceptions.PriceChangedException;
import my.ecommerce.exceptions.ProductNotAvailableException;
import my.ecommerce.repositories.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final CartService cartService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;


    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllByUser(Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        return orderRepository.findAllByUserId(user.getId());
    }

    @Transactional
    public Order placeOrder(OrderDTO orderDTO, Principal connectedUser) throws RuntimeException {
        OrderDetail orderDetail = null;
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        Cart cart = cartRepository.findCartByUserId(user.getId()).orElseThrow(() -> new EntityNotFoundException("Cart not found!"));
        var order = Order.builder()
                .orderDate(LocalDateTime.now())
                .user(user)
                .phone(orderDTO.getPhone())
                .state(orderDTO.getState())
                .region(orderDTO.getRegion())
                .city(orderDTO.getCity())
                .street(orderDTO.getStreet())
                .details(new ArrayList<>())
                .grandTotal(new BigDecimal(0))
                .orderDate(LocalDateTime.now())
                .build();
        orderRepository.save(order);
        for(CartItem item: cart.getItems()) {
            Product product = productRepository.findById(item.getProduct().getId()).orElseThrow(ProductNotAvailableException::new);
            if(product.getStock() < item.getQuantity())
                throw new ProductNotAvailableException();
            if(!Objects.equals(product.getPrice(), item.getProduct().getPrice()))
                throw new PriceChangedException();
            orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(item.getQuantity());
            orderDetailRepository.save(orderDetail);
            order.getDetails().add(orderDetail);
            order.setGrandTotal(order.getGrandTotal().add(item.getSubTotal()));
            product.setStock(product.getStock()-item.getQuantity());
            if(product.getStock() == 0) {
                product.setStatus(ProductStatus.NOT_AVAILABLE);
            }
        }
        if(order.getDetails().isEmpty()) {
            orderRepository.delete(order);
        } else {
            cartService.emptyCart(cart);
        }
        return orderRepository.findById(order.getId()).orElseThrow(() -> new EntityNotFoundException("Order not found!"));
    }

    public List<Order> findAllByUserId(Long id) {
        return orderRepository.findAllByUserId(id);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found!"));
    }
}
