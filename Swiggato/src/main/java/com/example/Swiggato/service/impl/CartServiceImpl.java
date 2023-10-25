package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.request.FoodItemRequest;
import com.example.Swiggato.dto.response.CartResponse;
import com.example.Swiggato.dto.response.CartStatusResponse;
import com.example.Swiggato.dto.response.FoodItemResponse;
import com.example.Swiggato.exception.CustomerNotFoundException;
import com.example.Swiggato.exception.MenuItemNotFoundException;
import com.example.Swiggato.model.*;
import com.example.Swiggato.repository.CartRepository;
import com.example.Swiggato.repository.CustomerRepository;
import com.example.Swiggato.repository.FoodItemRepository;
import com.example.Swiggato.repository.MenuItemRepository;
import com.example.Swiggato.service.CartService;
import com.example.Swiggato.transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    final CartRepository cartRepository;
    final CustomerRepository customerRepository;
    final MenuItemRepository menuItemRepository;
    final FoodItemRepository foodItemRepository;


    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CustomerRepository customerRepository, MenuItemRepository menuItemRepository, FoodItemRepository foodItemRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.menuItemRepository = menuItemRepository;
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    public CartResponse addFoodItemToCart(FoodItemRequest foodItemRequest) {

        // getting a customer
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNo(foodItemRequest.getCustomerMobileNo());
        if (optionalCustomer.isEmpty())
        {
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        Customer customer=optionalCustomer.get();


        //getting menuItem
        Optional<MenuItem> optionalMenuItem=menuItemRepository.findById(foodItemRequest.getMenuItemId());

        if (optionalMenuItem.isEmpty())
        {
            throw new MenuItemNotFoundException("Item not available in restaurant !!!")
        }

        MenuItem menuItem=optionalMenuItem.get();
        if(!menuItem.getRestaurant().isOpened() || !menuItem.isAvailable())
        {
            throw new MenuItemNotFoundException("Dish is out of stock for now");
        }

        //getting cart from customer
        Cart cart=customer.getCart();

        //having item for same restaurant
        if(cart.getFoodItemList().size()!=0)
        {
            Restaurant currRestaurant=cart.getFoodItemList().get(0).getMenuItem().getRestaurant();
            Restaurant newRestaurant=menuItem.getRestaurant();

            if (currRestaurant.equals(newRestaurant))
            {
                List<FoodItem> foodItemList=cart.getFoodItemList();
                for(FoodItem foodItem:foodItemList)
                {
                    foodItem.setCart(null);
                    foodItem.setOrderEntity(null);
                    foodItem.setMenuItem(null);
                }
                cart.setCartTotal(0);
                cart.getFoodItemList().clear();
                foodItemRepository.deleteAll(foodItemList);
            }
        }

        boolean alreadyExists=false;
        FoodItem saveFoodItems=null;

        if(cart.getFoodItemList().size()!=0)
        {
            for (FoodItem foodItem: cart.getFoodItemList())
            {
                saveFoodItems=foodItem;
                int curr=foodItem.getRequiredQuantity();
                foodItem.setRequiredQuantity(curr+foodItemRequest.getRequiredQuantity());
                alreadyExists=true;
                break;
            }
        }

        if(!alreadyExists)
        {
            FoodItem foodItem=FoodItem.builder()
                    .menuItem(menuItem)
                    .requiredQuantity(foodItemRequest.getRequiredQuantity())
                    .totalCost(foodItemRequest.getRequiredQuantity() * menuItem.getPrice())
                    .build();

            FoodItem saveFoodItem=foodItemRepository.save(foodItem);
            cart.getFoodItemList().add(saveFoodItem);
            menuItem.getFoodItemList().add(saveFoodItem);
            saveFoodItem.setCart(cart);
        }


        //getting cart total price

        double cartTotal=0;
        for(FoodItem food : cart.getFoodItemList())
        {
            cartTotal+=food.getRequiredQuantity()*food.getMenuItem().getPrice();
        }

        cart.setCartTotal(cartTotal);



        //save cart and MenuItem
        Cart saveCart=cartRepository.save(cart);
        MenuItem saveMenuItem=menuItemRepository.save(menuItem);

       // model to dto
        List<FoodItemResponse> foodItemResponseList=new ArrayList<>();
        for(FoodItem food: cart.getFoodItemList()){
           foodItemResponseList.add(.FoodToFoodResponse(food));
        }

        return CartStatusResponse.builder()
                .customerName(savedCart.getCustomer().getName())
                .customerMobile(savedCart.getCustomer().getMobileNo())
                .customerAddress(savedCart.getCustomer().getAddress())
                .foodList(foodResponseList)
                .restaurantName(savedMenuItem.getRestaurant().getName())
                .cartTotal(cartTotal)
                .build();



    }
}
