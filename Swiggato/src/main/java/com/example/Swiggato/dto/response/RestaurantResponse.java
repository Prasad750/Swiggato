package com.example.Swiggato.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RestaurantResponse {
    String name;
    String location;
    String contact;
    boolean opened;
    List<MenuItemResponse> menu;
}
