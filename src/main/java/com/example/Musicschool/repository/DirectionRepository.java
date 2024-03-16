package com.example.Musicschool.repository;

import com.example.Musicschool.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectionRepository extends JpaRepository<Direction,Long> {
    Optional<Direction> findFirstByName(String name);

    List<Direction> findAllByDepartment_IdOrderByLevelAsc(Long id);

    List<Direction> findAllByOrderByLevelAsc();





}
