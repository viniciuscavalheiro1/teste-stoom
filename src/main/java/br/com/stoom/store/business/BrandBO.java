package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.dto.BrandDTO;
import br.com.stoom.store.model.dto.CategoryDTO;
import br.com.stoom.store.repository.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandBO implements IBrandBO {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<BrandDTO> findAllBrandByActive() {
        List<Brand> brands = brandRepository.findAllByActiveTrue();
        return brands.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BrandDTO> findById(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        return Optional.ofNullable(modelMapper.map(brand, BrandDTO.class));
    }

    @Override
    public void deactivateCategory(Long categoryId) {
        Brand brand = brandRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        brand.deactivate();
        brandRepository.save(brand);
    }


    public BrandDTO convertToDto(Brand brand) {
        return modelMapper.map(brand, BrandDTO.class);
    }
}
