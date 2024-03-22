package br.com.stoom.store.controller.category;


import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.dto.CategoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryBO categoryBO;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/")
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryBO.findAllByActiveTrue());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<CategoryDTO>> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryBO.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        Category categorySave = categoryBO.saveCategory(category);
        return ResponseEntity.ok(category);
    }

    @PatchMapping("/delete/{categoryId}")
    public ResponseEntity<String> deactivateCategory(@PathVariable Long categoryId) {
        categoryBO.deactivateCategory(categoryId);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}
