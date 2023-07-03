package com.example.tasker.domain.category.repository;

import com.example.tasker.domain.category.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    Optional<Color> findByColorBackAndColorText(String colorBack, String colorText);
}
