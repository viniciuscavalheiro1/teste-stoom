package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.dto.BrandDTO;
import java.util.List;
import java.util.Optional;

public interface IBrandBO {

    List<BrandDTO> findAllBrandByActive();

    Optional<BrandDTO> findById(Long id);
    void deactivateCategory(Long brandId);

    BrandDTO saveBrand(BrandDTO brand);

}
