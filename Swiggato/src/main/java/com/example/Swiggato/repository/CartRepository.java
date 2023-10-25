package com.example.Swiggato.repository;

import com.example.Swiggato.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
