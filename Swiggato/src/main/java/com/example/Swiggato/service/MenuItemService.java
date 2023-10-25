package com.example.Swiggato.service;

import com.example.Swiggato.dto.response.FoodItemResponse;
import com.example.Swiggato.dto.response.MenuItemResponse;
import com.example.Swiggato.model.MenuItem;

import java.util.List;

public interface MenuItemService {
    List<FoodItemResponse> getAllFood(String category);

    List<MenuItem> getAllMainCourse(int restaurantId, int price);

    List<MenuItemResponse> getAllVeg(int restaurantId);
}
