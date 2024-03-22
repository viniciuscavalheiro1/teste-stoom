package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface ICategoryBO {

    Category saveCategory(Category category);

    List<Category> findAllByActiveTrue();

    Optional<CategoryDTO> findById(Long id);

    void deactivateCategory(Long categoryId);
}
