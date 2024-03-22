package br.com.stoom.store;
import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.controller.category.CategoryController;
import br.com.stoom.store.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    private CategoryBO categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll_ReturnsListOfCategories() {
        Category category1 = new Category(1L, "Category1", "Description1", Arrays.asList());
        Category category2 = new Category(2L, "Category2", "Description2", Arrays.asList());
        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryService.findAllByActiveTrue()).thenReturn(categories);

        ResponseEntity<List<Category>> response = categoryController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }


    @Test
    void deactivateCategory_DeactivatesCategory() {
        Long categoryId = 1L;

        ResponseEntity<String> response = categoryController.deactivateCategory(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Category deleted successfully", response.getBody());
        verify(categoryService, times(1)).deactivateCategory(categoryId);
    }
}
