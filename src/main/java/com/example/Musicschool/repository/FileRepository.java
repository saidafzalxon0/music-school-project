package com.example.Musicschool.repository;

import com.example.Musicschool.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,Long>{

    List<File> findAllByType(Integer type);
}
