package my.ecommerce.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import my.ecommerce.dtos.CategoryCreateDTO;
import my.ecommerce.entities.Category;
import my.ecommerce.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return findCategory(id);
    }

    private Category findCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found!"));
    }

    public void addCategory(CategoryCreateDTO categoryCreateDTO) {
        try {
            Category category = mapToEntityCreation(categoryCreateDTO);
            category.setName(categoryCreateDTO.name);
            categoryRepository.save(category);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Category mapToEntityCreation(CategoryCreateDTO categoryCreateDTO) {
        return modelMapper.map(categoryCreateDTO, Category.class);
    }

    public void editCategory(Long id, CategoryCreateDTO patch) {
            Category category = findCategory(id);
            try {
                category.setName(patch.getName());
                categoryRepository.save(category);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    public void deleteCategory(Long id) {
        Category category = findCategory(id);
        categoryRepository.delete(category);
    }
}
