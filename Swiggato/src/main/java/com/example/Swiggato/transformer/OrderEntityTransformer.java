package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.response.FoodItemResponse;
import com.example.Swiggato.dto.response.OrderEntityResponse;
import com.example.Swiggato.model.Cart;
import com.example.Swiggato.model.FoodItem;
import com.example.Swiggato.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderEntityTransformer {

    public static OrderEntity prepareOrderEntity(Cart cart)
    {
        return OrderEntity.builder()
                .orderId(UUID.randomUUID().toString())
                .orderTotal(cart.getCartTotal())
                .build();
    }

    public static OrderEntityResponse OrderEntityToOrderEntityResponse(OrderEntity orderEntity)
    {

        //FoodItem List

        List<FoodItemResponse> foodItemResponseList=new ArrayList<>();

        for (FoodItem f:orderEntity.getFoodItemList())
        {
            FoodItemResponse foodItemResponse=FoodItemResponse.builder()
                    .dishName(f.getMenuItem().getDishName())
                    .price(f.getMenuItem().getPrice())
                    .foodCategory(f.getMenuItem().getFoodCategory())
                    .veg(f.getMenuItem().isVeg())
                    .quantityAdded(f.getRequiredQuantity())
                    .build();

            foodItemResponseList.add(foodItemResponse);
        }

        return OrderEntityResponse.builder()
                .orderId(orderEntity.getOrderId())
                .orderTime(orderEntity.getOrderDate())
                .orderTotal(orderEntity.getOrderTotal())
                .customerName(orderEntity.getCustomer().getName())
                .customerMobileNo(orderEntity.getCustomer().getMobileNo())
                .deliveryPartnerName(orderEntity.getDeliveryPartner().getName())
                .deliveryPartnerMobileNo(orderEntity.getDeliveryPartner().getMobileNo())
                .restaurantName(orderEntity.getRestaurant().getName())
                .foodItemResponseList(foodItemResponseList)
                .build();
    }
}
