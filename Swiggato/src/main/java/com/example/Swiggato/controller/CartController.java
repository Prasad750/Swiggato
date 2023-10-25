package com.example.Swiggato.controller;


import com.example.Swiggato.dto.request.FoodItemRequest;
import com.example.Swiggato.dto.response.CartResponse;
import com.example.Swiggato.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    final CartServiceImpl cartServiceImpl;

    @Autowired
    public CartController(CartServiceImpl cartServiceImpl) {
        this.cartServiceImpl = cartServiceImpl;
    }


    @PostMapping("/add")
    public ResponseEntity addFoodItemToCart(@RequestBody FoodItemRequest foodItemRequest)
    {
        CartResponse cartResponse=cartServiceImpl.addFoodItemToCart(foodItemRequest);
    }
}
