package com.example.Musicschool.repository;

import com.example.Musicschool.entity.New;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface NewRepository extends JpaRepository<New,Long> {
    Page<New> findAllByDate(Pageable pageable, Date date);
}
