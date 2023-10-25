package com.example.Swiggato.model;


import com.example.Swiggato.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 2,message = "Validate name size too short")
    @Size(max = 20,message = "Validate name size too large")
    String name;

    @Email
    @Column(unique = true)
    String email;

    @Enumerated(EnumType.STRING)
    Gender gender;

    String address;

    @Size(max = 10,min = 10)
    String mobileNo;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<OrderEntity> orderEntities =new ArrayList<>();


}
