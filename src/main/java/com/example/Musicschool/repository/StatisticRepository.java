package com.example.Musicschool.repository;

import com.example.Musicschool.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic,Long> {

}
