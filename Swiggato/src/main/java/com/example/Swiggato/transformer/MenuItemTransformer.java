package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.request.MenuItemRequest;
import com.example.Swiggato.dto.response.MenuItemResponse;
import com.example.Swiggato.model.MenuItem;

public class MenuItemTransformer {
 public static MenuItemResponse MenuItemToMenuItemResponse(MenuItem menuItem)
 {
     return MenuItemResponse.builder()
             .dishName(menuItem.getDishName())
             .price(menuItem.getPrice())
             .foodCategory(menuItem.getFoodCategory())
             .veg(menuItem.isVeg())
             .build();
 }

 public static MenuItem MenuItemRequestToMenuItem(MenuItemRequest menuItemRequest)
 {
     return MenuItem.builder()
             .dishName(menuItemRequest.getDishName())
             .price(menuItemRequest.getPrice())
             .foodCategory(menuItemRequest.getFoodCategory())
             .veg(menuItemRequest.isVeg())
             .available(menuItemRequest.isAvailable())
             .build();
 }
}
