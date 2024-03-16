package com.example.Musicschool.repository;

import com.example.Musicschool.entity.Direction;
import com.example.Musicschool.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Optional<Partner> findFirstByName(String name);
    Optional<Partner> findFirstByLinkWebsite(String name);
}
