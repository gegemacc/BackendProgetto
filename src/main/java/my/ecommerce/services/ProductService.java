package my.ecommerce.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import my.ecommerce.dtos.ProductCreateDTO;
import my.ecommerce.entities.Category;
import my.ecommerce.entities.Product;
import my.ecommerce.enums.ProductStatus;
import my.ecommerce.repositories.CategoryRepository;
import my.ecommerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public Product addProduct(ProductCreateDTO productCreateDTO) {
        try {
            Product product = mapToEntityCreation(productCreateDTO);
            product.setStatus(ProductStatus.AVAILABLE);
            product.setCreated(LocalDateTime.now());
            return productRepository.save(product);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Product mapToEntityCreation(ProductCreateDTO productCreateDTO) {
        return modelMapper.map(productCreateDTO, Product.class);
    }

    public ProductCreateDTO editProduct(Long id, ProductCreateDTO patch) {
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

    public Page<Product> findAll(int page, int size, String searchKey) {
        if(searchKey == "") {
            return productRepository.findAll(PageRequest.of(page, size));
        } else {
            return productRepository.findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchKey,searchKey,PageRequest.of(page,size));
        }

    }

    private ProductCreateDTO mapToDto(Product product) {
        return modelMapper.map(product, ProductCreateDTO.class);
    }

    public Product getProductById(Long id) {
        return findProduct(id);
    }

    public List<Product> findAllByCategory(Long id) {
        return productRepository.findAllByCategoryId(id);
    }
}