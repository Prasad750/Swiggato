package com.example.Swiggato.dto.response;

import com.example.Swiggato.model.FoodItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartResponse {

    double cartTotal;
    List<FoodItemResponse> foodItemResponseList;
}
