package com.developers.ecommerceapp.ekart.model;

import javax.validation.constraints.NotNull;

import com.developers.ecommerceapp.ekart.dao.Product;

import lombok.Data;

@Data
public class ProductDto {
	
	private Integer id;
    private @NotNull String name;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Integer categoryId;

    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setCategoryId(product.getCategory().getId());
    }

    public ProductDto(@NotNull String name, @NotNull double price, @NotNull String description, @NotNull Integer categoryId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }

    public ProductDto() {
    }

}
