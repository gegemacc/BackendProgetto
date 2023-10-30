package my.ecommerce.services;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import my.ecommerce.dtos.ProductDTO;
import my.ecommerce.dtos.UserDTO;
import my.ecommerce.entities.Product;
import my.ecommerce.enums.ProductStatus;
import my.ecommerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductDTO addProduct(ProductDTO productDTO) {
        try {
            Product product = mapToEntityCreation(productDTO);
            product.setStatus(ProductStatus.AVAILABLE);
            product.setCreated(LocalDateTime.now());
            return mapToDto(productRepository.save(product));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Product mapToEntityCreation(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    public ProductDTO editProduct(Long id, ProductDTO patch) {
        Product product = findProduct(id);
        try {
            product.setName(patch.getName());
            product.setCategory(patch.getCategory());
            product.setPrice(patch.getPrice());
            product.setImage(patch.getImage());
            product.setStock(patch.getStock());
            product.setDescription(patch.getDescription());
            product.setUpdated(LocalDateTime.now());
            return mapToDto(productRepository.save(product));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void deleteProduct(Long id) {
        Product product = findProduct(id);
        productRepository.delete(product);
    }

    private Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
    }

    public Page<ProductDTO> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size)).map(this::mapToDto);
    }

    private ProductDTO mapToDto(Product product) {
        return modelMapper.map(product,ProductDTO.class);
    }

    public ProductDTO getProductById(Long id) {
        Product product = findProduct(id);
        return mapToDto(product);
    }
}