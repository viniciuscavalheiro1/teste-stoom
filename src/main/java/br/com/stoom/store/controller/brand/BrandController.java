package br.com.stoom.store.controller.brand;

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.model.dto.BrandDTO;
import br.com.stoom.store.model.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    private BrandBO brandService;

    @GetMapping("/")
    public ResponseEntity<List<BrandDTO>> findAll() {
        return ResponseEntity.ok(brandService.findAllBrandByActive());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<BrandDTO>> getBrandById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.findById(id));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandDTO> save(@RequestBody BrandDTO brand) {
        BrandDTO categorySave = brandService.saveBrand(brand);
        return ResponseEntity.ok(brand);
    }

    @PatchMapping("/delete/{brandId}")
    public ResponseEntity<String> deactivateCategory(@PathVariable Long brandId) {
        brandService.deactivateCategory(brandId);
        return new ResponseEntity<>("Brand deleted successfully", HttpStatus.OK);
    }
}
