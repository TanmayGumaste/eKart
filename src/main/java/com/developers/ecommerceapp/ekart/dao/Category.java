package com.developers.ecommerceapp.ekart.dao;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;


@Entity
@Table(name = "categories")
@Data
public class Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4265857406122310526L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	private Integer id;

	@Column(name = "category_name")
	private @NotBlank String categoryName;

	private @NotBlank String description;
	

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	Set<Product> products;
	public Category() {
	}
	
	public Category(@NotBlank String categoryName, @NotBlank String description) {
		this.categoryName = categoryName;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "customer {category id=" + id + ", category name='" + categoryName + "', description='" + description + "'}";
	}

	

}
