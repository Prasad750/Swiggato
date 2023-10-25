package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.request.MenuItemRequest;
import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.exception.RestaurantNotFoundException;
import com.example.Swiggato.model.MenuItem;
import com.example.Swiggato.model.Restaurant;
import com.example.Swiggato.repository.RestaurantRepository;
import com.example.Swiggato.service.RestaurantService;
import com.example.Swiggato.transformer.MenuItemTransformer;
import com.example.Swiggato.transformer.RestaurantTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RestaurantServiceImpl implements RestaurantService {

    final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {

        //reqDto -> model
        Restaurant restaurant= RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);

        //save
        Restaurant saveRestaurant=restaurantRepository.save(restaurant);

        //model -> respDto
        return RestaurantTransformer.RestaurantToRestaurantResponse(saveRestaurant);
    }

    @Override
    public String changeRestaurantStatus(int id) {

        Optional<Restaurant> optionalRestaurant=restaurantRepository.findById(id);

        if(optionalRestaurant.isEmpty())
        {
            throw new RestaurantNotFoundException("Restaurant not Exist");
        }

        Restaurant restaurant=optionalRestaurant.get();
        restaurant.setOpened(!restaurant.isOpened());

        //save to update
        Restaurant saveRestaurant=restaurantRepository.save(restaurant);

        if(saveRestaurant.isOpened())
        {
            return "Restaurant is Open now !!!";
        }
        return "Restaurant is Closed";
    }

    @Override
    public RestaurantResponse addFoodToRestaurantMenu(MenuItemRequest menuItemRequest) {

        // finding restaurant

        Optional<Restaurant> optionalRestaurant=restaurantRepository.findById(menuItemRequest.getRestaurantId());

        if (optionalRestaurant.isEmpty())
        {
            throw new RestaurantNotFoundException("Restaurant not Exist");
        }
         Restaurant restaurant=optionalRestaurant.get();

        //reqDto -> model
        MenuItem menuItem = MenuItemTransformer.MenuItemRequestToMenuItem(menuItemRequest);
        menuItem.setRestaurant(restaurant);


        //adding food item in restaurant menu
        restaurant.getMenuItemList().add(menuItem);

        //saving restaurant as food is child entity
        Restaurant saveRestaurant=restaurantRepository.save(restaurant);

        //model -> respDto
        return RestaurantTransformer.RestaurantToRestaurantResponse(saveRestaurant);





    }
}
