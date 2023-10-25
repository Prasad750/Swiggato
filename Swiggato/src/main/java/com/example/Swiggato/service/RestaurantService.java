package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.MenuItemRequest;
import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.RestaurantResponse;

public interface RestaurantService {
    RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);

    String changeRestaurantStatus(int id);

    RestaurantResponse addFoodToRestaurantMenu(MenuItemRequest menuItemRequest);
}
