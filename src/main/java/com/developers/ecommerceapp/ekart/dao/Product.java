package com.developers.ecommerceapp.ekart.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.developers.ecommerceapp.ekart.model.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7114949706832825041L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    private Integer id;

    private @NotNull String name;
    private @NotNull double price;
    private @NotNull String description;
    

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Cart> carts;
    

    public Product(ProductDto productDto, Category category) {
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.category = category;
    }

    public Product(String name, double price, String description, Category category) {
        super();
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Product() {
    }
    
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

}
