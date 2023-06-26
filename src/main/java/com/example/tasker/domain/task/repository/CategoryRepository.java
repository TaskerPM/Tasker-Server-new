package com.example.tasker.domain.task.repository;

import com.example.tasker.domain.task.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryId(Long categoryId);

}
