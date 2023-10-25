package com.example.Swiggato.model;

import com.example.Swiggato.Enum.RestaurantCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name ="restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String location;

    @Enumerated(EnumType.STRING)
    RestaurantCategory restaurantCategory;

    String contactNo;
    boolean opened;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    List<MenuItem> menuItemList =new ArrayList<>();

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    List<OrderEntity> orderEntities =new ArrayList<>();
}