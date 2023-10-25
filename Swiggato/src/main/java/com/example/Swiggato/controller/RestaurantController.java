package com.example.Swiggato.controller;


import com.example.Swiggato.dto.request.MenuItemRequest;
import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.service.impl.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    final RestaurantServiceImpl restaurantServiceImpl;

    @Autowired
    public RestaurantController(RestaurantServiceImpl restaurantServiceImpl) {
        this.restaurantServiceImpl = restaurantServiceImpl;
    }

    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest)
    {
        RestaurantResponse response= restaurantServiceImpl.addRestaurant(restaurantRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    @PutMapping("/open/status")
    public ResponseEntity changeRestaurantStatus(@RequestParam("id") int id)
    {
        String response=restaurantServiceImpl.changeRestaurantStatus(id);
        return new ResponseEntity(response,HttpStatus.ACCEPTED);

    }

    @PostMapping ("/add/menuItem")
    public ResponseEntity addFoodToRestaurantMenu(@RequestBody MenuItemRequest menuItemRequest)
    {
        RestaurantResponse response=restaurantServiceImpl.addFoodToRestaurantMenu(menuItemRequest);
        return new ResponseEntity(response,HttpStatus.CREATED);
    }

    //get menu of restaurant
}
