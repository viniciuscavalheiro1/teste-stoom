package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.CategoryDTO;
import br.com.stoom.store.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryBO implements ICategoryBO {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllByActiveTrue() {
        return categoryRepository.findAllByActiveTrue();
    }

    @Override
    public Optional<CategoryDTO> findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return Optional.ofNullable(modelMapper.map(category, CategoryDTO.class));
    }

    @Override
    public void deactivateCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        category.deactivate();
        categoryRepository.save(category);
    }
}
