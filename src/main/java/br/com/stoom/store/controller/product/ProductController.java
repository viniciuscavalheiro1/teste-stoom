package br.com.stoom.store.controller.product;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.BrandDTO;
import br.com.stoom.store.model.dto.CategoryDTO;
import br.com.stoom.store.model.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductBO productService;


    @GetMapping(value = "/")
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> products = productService.findAllProductsByActive();
        if(!products.isEmpty())
            return new ResponseEntity<>(products, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<ProductDTO>> findProductById(@PathVariable Long id) {

        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO product) {
        ProductDTO categorySave = productService.saveProducrt(product);
        return ResponseEntity.ok(categorySave);
    }

    @PatchMapping("/delete/{productId}")
    public ResponseEntity<String> deactivateProduct(@PathVariable Long productId) {
        productService.deactivateProduct(productId);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }


}
