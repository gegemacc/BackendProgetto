package my.ecommerce.services;

import lombok.RequiredArgsConstructor;
import my.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
}
