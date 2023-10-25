package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.MenuItemResponse;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantTransformer {

    public static Restaurant RestaurantRequestToRestaurant(RestaurantRequest restaurantRequest)
    {
        return Restaurant.builder()
                .name(restaurantRequest.getName())
                .location(restaurantRequest.getLocation())
                .restaurantCategory(restaurantRequest.getRestaurantCategory())
                .contactNo(restaurantRequest.getContactNo())
                .opened(true)
                .menuItemList(new ArrayList<>())
                .orderEntities(new ArrayList<>())
                .build();
    }

    public static RestaurantResponse RestaurantToRestaurantResponse(Restaurant restaurant)
    {
        List<MenuItemResponse> menu = restaurant.getMenuItemList()
                .stream()
                .map(menuItem -> MenuItemTransformer.MenuItemToMenuItemResponse(menuItem))
                .collect(Collectors.toList());

        return RestaurantResponse.builder()
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .contact(restaurant.getContactNo())
                .opened(restaurant.isOpened())
                .menu(menu)
                .build();
    }
}
