package com.example.tasker.domain.category.repository;

import com.example.tasker.domain.category.entity.Category;
import com.example.tasker.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByUserAndName(User findUser, String name);
    List<Category> findByUser(User user);
}
