package com.example.tasker.domain.category.repository;

import com.example.tasker.domain.category.entity.Category;
import com.example.tasker.domain.category.entity.Color;
import com.example.tasker.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);

    Optional<Category> findByUserAndNameAndColor(User user, String name, Color color);
}
