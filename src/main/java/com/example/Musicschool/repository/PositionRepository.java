package com.example.Musicschool.repository;

import com.example.Musicschool.entity.Direction;
import com.example.Musicschool.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {
    Optional<Position> findFirstByName(String name);
}
