package com.example.Swiggato.service.impl;

import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.dto.response.MenuItemResponse;
import com.example.Swiggato.exception.RestaurantNotFoundException;
import com.example.Swiggato.model.MenuItem;
import com.example.Swiggato.model.Restaurant;
import com.example.Swiggato.repository.MenuItemRepository;
import com.example.Swiggato.repository.RestaurantRepository;
import com.example.Swiggato.service.MenuItemService;
import com.example.Swiggato.transformer.MenuItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    final MenuItemRepository menuItemRepository;
    final RestaurantRepository restaurantRepository;

    @Autowired
    public MenuItemServiceImpl(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public List<MenuItemResponse> getAllFood(String category) {

        List<MenuItem> menuItemList=menuItemRepository.findAllByFoodCategory(FoodCategory.valueOf(category));

        return menuItemList.stream()
                .map(menuItem -> MenuItemTransformer.MenuItemToMenuItemResponse(menuItem))
                .collect(Collectors.toList());

    }

    @Override
    public List<MenuItem> getAllMainCourse(int restaurantId, int price) {

        Optional<Restaurant> optionalRestaurant= restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isEmpty())
        {
            throw new RestaurantNotFoundException("Restaurant Not exist");
        }

        Restaurant restaurant=optionalRestaurant.get();

        List<MenuItem> menuItemList=restaurant.getMenuItemList();

        return menuItemList.stream()
                .filter(menuItem -> menuItem.getFoodCategory().equals(FoodCategory.MAIN_COURSE) && menuItem.getPrice()>price)
                .collect(Collectors.toList());

    }

    @Override
    public List<MenuItemResponse> getAllVeg(int restaurantId) {
        Optional<List<MenuItem>>
    }
}
