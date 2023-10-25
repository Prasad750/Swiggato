package com.example.Swiggato.dto.response;

import com.example.Swiggato.model.FoodItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderEntityResponse {
    String orderId;
    double orderTotal;

    Date orderTime;

    String customerName;
    String customerMobileNo;

    String deliveryPartnerName;

    String deliveryPartnerMobileNo;

    String restaurantName;

    List<FoodItemResponse> foodItemResponseList;
}
