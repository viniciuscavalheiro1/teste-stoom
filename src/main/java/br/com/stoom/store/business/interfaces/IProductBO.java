package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.dto.ProductDTO;
import java.util.List;

public interface IProductBO {

    List<ProductDTO> findAllProductsByActive();

    void deactivateProduct(Long productId);

    ProductDTO saveProducrt(ProductDTO product);

}
