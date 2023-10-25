package com.example.Swiggato.controller;


import com.example.Swiggato.dto.response.FoodItemResponse;
import com.example.Swiggato.dto.response.MenuItemResponse;
import com.example.Swiggato.service.impl.MenuItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menuItem")
public class MenuItemController {

    final MenuItemServiceImpl menuItemServiceImpl;

    @Autowired
    public MenuItemController(MenuItemServiceImpl menuItemServiceImpl) {
        this.menuItemServiceImpl = menuItemServiceImpl;
    }

    //get all foods of particular category
    @GetMapping("/all-food/category/{category}")
    public ResponseEntity getAllFood(@PathVariable ("category") String category)
    {
        List<MenuItemResponse> responses=menuItemServiceImpl.getAllFood(category);
        return new ResponseEntity<>(responses, HttpStatus.FOUND);
    }
    //get all MAIN_COURSE items with price above x from particular restaurant
    @GetMapping("/all/maincourse/")
    public ResponseEntity getAllMainCourse(@RequestParam("restaurantId") int restaurantId,@RequestParam("price") int price )
    {
        try {
            List<MenuItemResponse> responses=menuItemServiceImpl.getAllMainCourse(restaurantId,price);
            return new ResponseEntity(responses,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    //get all veg food of restaurant
    @GetMapping("/veg")
    public ResponseEntity getAllVeg(@RequestParam("restaurantId") int restaurantId)
    {
        try {
            List<MenuItemResponse> responses=menuItemServiceImpl.getAllVeg(restaurantId);
            return new ResponseEntity<>(responses,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    //get all non-veg foods of restaurant
    //get top 5 cheapest food from particular restaurant
    //get top costliest food from particular restaurant
    //
}
