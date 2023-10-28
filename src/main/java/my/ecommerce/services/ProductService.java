package my.ecommerce.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import my.ecommerce.dtos.ProductCreateDTO;
import my.ecommerce.entities.Product;
import my.ecommerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public Product addProduct(ProductCreateDTO productCreateDTO) {
        Product product = mapToEntityCreation(productCreateDTO);
        if(productCreateDTO.)
    }

    private Product mapToEntityCreation(ProductCreateDTO productCreateDTO) {
        return modelMapper.map(productCreateDTO, Product.class);
    }

    public Product editProduct(Long id, ProductCreateDTO productCreateDTO) {
        return null;
    }

    public void deleteProduct(Long id) {
        Product product = findProduct(id);
        productRepository.delete(product);
    }

    private Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}