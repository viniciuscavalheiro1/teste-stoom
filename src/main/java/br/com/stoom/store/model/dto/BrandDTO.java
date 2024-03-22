package br.com.stoom.store.model.dto;

import br.com.stoom.store.model.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

public class BrandDTO implements Serializable {

    private String name;
    private String description;
    @JsonBackReference
    private List<ProductDTO> products;

    public BrandDTO(String name, String description, List<ProductDTO> products) {
        this.name = name;
        this.description = description;
        this.products = products;
    }

    public BrandDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
