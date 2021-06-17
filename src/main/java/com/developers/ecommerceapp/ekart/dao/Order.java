package com.developers.ecommerceapp.ekart.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.developers.ecommerceapp.ekart.model.PlaceOrderDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="orders")
@Data
public class Order implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5421516233311262555L;


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    private Integer id;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "Cust_ID")
    private CustomerEntity user;

    public Order() {
    }

    public Order(PlaceOrderDto orderDto, CustomerEntity user){
        this.user = user;
        this.createdDate = new Date();
        this.totalPrice = orderDto.getTotalPrice();
    }
}
