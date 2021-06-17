package com.developers.ecommerceapp.ekart.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developers.ecommerceapp.ekart.dao.Cart;
import com.developers.ecommerceapp.ekart.dao.CustomerEntity;
import com.developers.ecommerceapp.ekart.dao.Product;
import com.developers.ecommerceapp.ekart.exceptions.CartItemNotExistException;
import com.developers.ecommerceapp.ekart.model.AddToCartDto;
import com.developers.ecommerceapp.ekart.model.CartDto;
import com.developers.ecommerceapp.ekart.model.CartItemDto;
import com.developers.ecommerceapp.ekart.repository.CartRepository;

@Service
@Transactional
public class CartService {

    @Autowired
    private  CartRepository cartRepository;

    public CartService(){}

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartDto addToCartDto, Product product, CustomerEntity customer){
        Cart cart = new Cart(product, addToCartDto.getQuantity(), customer);
        List<Cart> cartList = cartRepository.findAllByCustomerOrderByCreatedDateDesc(customer);
//        Optional<Cart> cartOp = cartList.stream().filter(i-> i.getProduct().getId()==product.getId()).findAny();
//        Optional<Cart> cartop1 = product.getCarts().stream().filter(j->j.getId()==cartOp.get().getId()).findAny();
        
        cartRepository.save(cart);
    }


    public CartDto listCartItems(CustomerEntity customer) {
        List<Cart> cartList = cartRepository.findAllByCustomerOrderByCreatedDateDesc(customer);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getProduct().getPrice()* cartItemDto.getQuantity());
        }
        CartDto cartDto = new CartDto(cartItems,totalCost);
        return cartDto;
    }


    public static CartItemDto getDtoFromCart(Cart cart) {
        CartItemDto cartItemDto = new CartItemDto(cart);
        return cartItemDto;
    }


    public void updateCartItem(AddToCartDto cartDto, CustomerEntity customer,Product product){
        Cart cart = cartRepository.getOne(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }

    public void deleteCartItem(int id,int userId) throws CartItemNotExistException {
        if (!cartRepository.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);

    }

    public void deleteCartItems(int userId) {
        cartRepository.deleteAll();
    }


    public void deleteUserCartItems(CustomerEntity user) {
        cartRepository.deleteByCustomer(user);
    }
}


