package com.example.Musicschool.repository;

import com.example.Musicschool.entity.Government;
import com.example.Musicschool.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GovernmentRepository  extends JpaRepository<Government, Long> {
    Optional<Government> findFirstByName(String name);
    Optional<Government> findFirstByLinkWebsite(String name);
}
