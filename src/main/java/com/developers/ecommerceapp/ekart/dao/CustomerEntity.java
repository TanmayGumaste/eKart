package com.developers.ecommerceapp.ekart.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class CustomerEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6687395565537197046L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	 @Column(name = "Cust_ID")
	private Integer custId;

	@Column(nullable = false, length = 50)
	private String firstName;
	@Column(nullable = false, length = 50)
	private String lastName;
	@Column(nullable = false, length = 120, unique = true)
	private String email;
	@Column(nullable = false)
	private String encryptedPassword;
	@Column(nullable = false)
	private String contactno;
	private String address;

}
