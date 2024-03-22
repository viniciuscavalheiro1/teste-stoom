package br.com.stoom.store;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.controller.product.ProductController;
import br.com.stoom.store.model.dto.ProductDTO;
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

class ProductControllerTest {

    @Mock
    private ProductBO productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll_ReturnsListOfProducts() {
        ProductDTO product1 = new ProductDTO(1L, "SKU1", "Product1", null, null, null);
        ProductDTO product2 = new ProductDTO(2L, "SKU2", "Product2", null, null, null);
        List<ProductDTO> products = Arrays.asList(product1, product2);

        when(productService.findAllProductsByActive()).thenReturn(products);

        ResponseEntity<List<ProductDTO>> response = productController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void findProductById_ReturnsProductById() {
        Long productId = 1L;
        ProductDTO product = new ProductDTO(productId, "SKU1", "Product1", null, null, null);
        Optional<ProductDTO> optionalProduct = Optional.of(product);

        when(productService.findById(productId)).thenReturn(optionalProduct);

        ResponseEntity<Optional<ProductDTO>> response = productController.findProductById(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optionalProduct, response.getBody());
    }

    @Test
    void save_CreatesNewProduct() {
        ProductDTO product = new ProductDTO(1L, "SKU1", "Product1", null, null, null);

        when(productService.saveProducrt(product)).thenReturn(product);

        ResponseEntity<ProductDTO> response = productController.save(product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void deactivateProduct_DeactivatesProduct() {
        Long productId = 1L;

        ResponseEntity<String> response = productController.deactivateProduct(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product deleted successfully", response.getBody());
        verify(productService, times(1)).deactivateProduct(productId);
    }
}
