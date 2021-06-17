package com.developers.ecommerceapp.ekart.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developers.ecommerceapp.ekart.dao.CustomerEntity;
import com.developers.ecommerceapp.ekart.dao.Product;
import com.developers.ecommerceapp.ekart.exceptions.CartItemNotExistException;
import com.developers.ecommerceapp.ekart.exceptions.ProductNotExistException;
import com.developers.ecommerceapp.ekart.model.AddToCartDto;
import com.developers.ecommerceapp.ekart.model.ApiResponse;
import com.developers.ecommerceapp.ekart.model.CartDto;
import com.developers.ecommerceapp.ekart.repository.CustomerRepository;
import com.developers.ecommerceapp.ekart.service.CartService;
import com.developers.ecommerceapp.ekart.service.ProductService;
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private ProductService productService;
    @PostMapping("/add/{id}")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,@PathVariable("id") Integer cust_Id) throws  ProductNotExistException {
    	CustomerEntity customer=customerRepository.findByCustId(cust_Id);
    	Product product = productService.getProductById(addToCartDto.getProductId());
    	cartService.addToCart(addToCartDto, product, customer);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart",product.getId()), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCartItems(@PathVariable("id") Integer cust_Id)  {
    	CustomerEntity customer=customerRepository.findByCustId(cust_Id);
        CartDto cartDto = cartService.listCartItems(customer);
        return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{cartItemId}/{id}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID,@PathVariable("id") Integer cust_Id) throws  CartItemNotExistException {
        cartService.deleteCartItem(itemID, cust_Id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed",itemID), HttpStatus.OK);
    }
	
}
