package br.com.stoom.store;

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.controller.brand.BrandController;
import br.com.stoom.store.model.dto.BrandDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BrandControllerTest {
    @Mock
    private BrandBO brandService;

    @InjectMocks
    private BrandController brandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll_ReturnsListOfBrands() {
        BrandDTO brand1 = new BrandDTO("Brad1", "Brand1");
        BrandDTO brand2 = new BrandDTO("Brand2", "Brand2");
        List<BrandDTO> brands = Arrays.asList(brand1, brand2);

        when(brandService.findAllBrandByActive()).thenReturn(brands);

        ResponseEntity<List<BrandDTO>> response = brandController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brands, response.getBody());
    }

}
