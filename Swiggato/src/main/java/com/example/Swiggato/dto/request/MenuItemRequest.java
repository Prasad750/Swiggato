package com.example.Swiggato.dto.request;

import com.example.Swiggato.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MenuItemRequest {
    // this req Dto is only add foodItems in restaurant only
    //we only consider foodItem attributes which related to restaurant

    int restaurantId;
    String dishName;
    double price;
    FoodCategory foodCategory;
    boolean veg;
    boolean available;
}
