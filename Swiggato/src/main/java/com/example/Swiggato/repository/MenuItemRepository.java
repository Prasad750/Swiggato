package com.example.Swiggato.repository;

import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {
    List<MenuItem> findAllByFoodCategory(FoodCategory foodCategory);
}
