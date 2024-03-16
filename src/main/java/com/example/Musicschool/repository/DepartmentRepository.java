package com.example.Musicschool.repository;

import com.example.Musicschool.entity.Department;
import com.example.Musicschool.projection.DepartmentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {


    Optional<Department> findFirstByName(String name);
    @Query(value = "select department.id department_id,department.name department_name,direction.id direction_id,direction.name direction_name from department inner join direction on department.id = direction.department_id order by direction.level",nativeQuery = true)
    List<DepartmentProjection> listAllDepartmentDirection();

    @Query(value = "select department.id department_id,department.name department_name,direction.id direction_id,direction.name direction_name  from department inner join direction on department.id = direction.department_id where department.id = :departmentId order by direction.level",nativeQuery = true)
    List<DepartmentProjection> listAllDirectionByDepartmentId(@Param("departmentId") Long departmentId);

    List<Department> findAllByOrderByIdAsc();




}
