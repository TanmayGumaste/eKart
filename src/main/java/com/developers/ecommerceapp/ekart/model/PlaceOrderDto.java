package com.developers.ecommerceapp.ekart.model;

import javax.validation.constraints.NotNull;

import com.developers.ecommerceapp.ekart.dao.CustomerEntity;
import com.developers.ecommerceapp.ekart.dao.Order;

public class PlaceOrderDto {
    private Integer id;
    private @NotNull CustomerEntity userId;
    private @NotNull Double totalPrice;

    public PlaceOrderDto() {
    }

    public PlaceOrderDto(Order order) {
        this.setId(order.getId());
        this.setUser(order.getUser());
        this.setTotalPrice(order.getTotalPrice());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerEntity getUser() {
        return userId;
    }

    public void setUser(CustomerEntity user) {
        this.userId = user;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
