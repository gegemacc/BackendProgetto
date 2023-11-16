package my.ecommerce.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import my.ecommerce.entities.Cart;
import my.ecommerce.entities.CartItem;
import my.ecommerce.entities.Product;
import my.ecommerce.entities.User;
import my.ecommerce.exceptions.ProductNotAvailableException;
import my.ecommerce.repositories.CartItemRepository;
import my.ecommerce.repositories.CartRepository;
import my.ecommerce.repositories.ProductRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void addToCart(Long productId, int quantity, Principal connectedUser) throws RuntimeException {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        Cart cart = cartRepository.findCartByUserId(user.getId()).orElseThrow(() -> new EntityNotFoundException("Cart not found!"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
        CartItem cartItem = cartItemRepository.findByProductId(productId);
        if(product.getStock() - quantity >= 0) {
            if(cartItem != null) {
                editCart(cartItem.getId(), cartItem.getQuantity(), cartItem.getQuantity() +1, connectedUser);
            } else {
                cartItem = CartItem.builder()
                        .product(product)
                        .quantity(quantity)
                        .subTotal((product.getPrice().multiply(new BigDecimal(quantity))))
                        .build();
                cartItemRepository.save(cartItem);
                cart.getItems().add(cartItem);
                int newQuantity = cart.getQuantity() + cartItem.getQuantity();
                BigDecimal newGrandTotal = cart.getGrandTotal().add(cartItem.getSubTotal());
                updateQuantity(cart, newQuantity);
                updateGrandTotal(cart, newGrandTotal);
                cartRepository.save(cart);
            }
        } else {
            throw new ProductNotAvailableException();
        }
    }

    private void updateQuantity(Cart cart, int newQuantity) {
        cart.setQuantity(newQuantity);
    }

    private void updateGrandTotal(Cart cart, BigDecimal newGrandTotal) {
        cart.setGrandTotal(newGrandTotal);
    }

    @Transactional
    public void editCart(Long cartItemId, int oldQuantity, int newQuantity, Principal connectedUser) throws RuntimeException {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        Cart cart = cartRepository.findCartByUserId(user.getId()).orElseThrow(() -> new EntityNotFoundException("Cart not found!"));
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new EntityNotFoundException("CartItem not found!"));
        Product product = productRepository.findById(cartItem.getProduct().getId()).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
        if(product.getStock() - newQuantity >= 0) {

            int newCartQuantity;
            BigDecimal newSubTotal;
            BigDecimal newGrandTotal;
            int x = oldQuantity - newQuantity;
            if(x > 0) {
                newCartQuantity = cart.getQuantity() - x;
                newSubTotal = new BigDecimal(newQuantity).multiply(product.getPrice());
                newGrandTotal = cart.getGrandTotal().subtract(new BigDecimal(x).multiply(product.getPrice()));
            } else {
                x = newQuantity - oldQuantity;
                newCartQuantity = cart.getQuantity() + x;
                newSubTotal = new BigDecimal(newQuantity).multiply(product.getPrice());
                newGrandTotal = cart.getGrandTotal().add(new BigDecimal(x).multiply(product.getPrice()));
            }
            cartItem.setQuantity(newQuantity);
            cartItem.setSubTotal(newSubTotal);
            cartItemRepository.save(cartItem);
            cart.getItems().add(cartItem);
            updateQuantity(cart, newCartQuantity);
            updateGrandTotal(cart, newGrandTotal);
            cartRepository.save(cart);
        } else {
            throw new ProductNotAvailableException();
        }
    }

    public void deleteFromCart(Long cartItemId, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        Cart cart = cartRepository.findCartByUserId(user.getId()).orElseThrow(() -> new EntityNotFoundException("Cart not found!"));
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new EntityNotFoundException("CartItem not found!"));
        int newCartQuantity = cart.getQuantity() - cartItem.getQuantity();
        BigDecimal newGrandTotal = cart.getGrandTotal().subtract(new BigDecimal(cartItem.getQuantity()).multiply(cartItem.getProduct().getPrice()));
        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        updateQuantity(cart, newCartQuantity);
        updateGrandTotal(cart, newGrandTotal);
        cartRepository.save(cart);
    }

    public Cart getCart(Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        return cartRepository.findCartByUserId(user.getId()).orElseThrow(() -> new EntityNotFoundException("Cart not found!"));
    }

    public void buildCart(User user) {
        var cart = Cart.builder()
                .user(user)
                .quantity(0)
                .items(new ArrayList<>())
                .grandTotal(BigDecimal.valueOf(0))
                .build();
        cartRepository.save(cart);
    }

    public void emptyCart(Cart cart) {
        cartItemRepository.deleteAll(cart.getItems());
        cart.setItems(new ArrayList<>());
        cart.setQuantity(0);
        cart.setGrandTotal(BigDecimal.valueOf(0));
    }
}
