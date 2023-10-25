package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.FoodItemRequest;
import com.example.Swiggato.dto.response.CartResponse;

public interface CartService {
    CartResponse addFoodItemToCart(FoodItemRequest foodItemRequest);
}
