package com.developers.ecommerceapp.ekart.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "cart")
@Data
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 969679331961259786L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	private Integer id;

	@Column(name = "created_date")
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

	@JsonIgnore
	@OneToOne(targetEntity = CustomerEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "CUST_ID")
	private CustomerEntity customer;

	private int quantity;

	  public Cart() {
	    }
	public Cart(Product product, int quantity, CustomerEntity customer) {
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
		this.createdDate = new Date();
	}

}
