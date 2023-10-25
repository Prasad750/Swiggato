package com.example.Swiggato.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FoodItemRequest {

    double requiredQuantity;
    String customerMobileNo;
    int menuItemId;
}
