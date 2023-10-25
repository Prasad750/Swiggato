package com.example.Swiggato.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartStatusResponse {
    String customerName;
    String address;
    String mobileNo;
    double cartTotal;
    List<FoodItemResponse> foodItemResponseList;
    String restaurantName;
}
