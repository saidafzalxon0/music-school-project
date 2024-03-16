package com.example.Musicschool.repository;

import com.example.Musicschool.entity.Direction;
import com.example.Musicschool.entity.Employee;
import com.example.Musicschool.projection.EmployeeIdProjection;
import com.example.Musicschool.projection.EmployeeLevelProjection;
import com.example.Musicschool.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query(value = "SELECT employee.id AS id, employee.full_name AS fullName, employee.about AS about,\n" +
            "                       employee.editor AS editor, position.id AS position_id, position.name AS position_name,\n" +
            "                    direction.id AS direction_id, direction.name AS direction_name,\n" +
            "                        department.id AS department_id, department.name AS department_name,\n" +
            "                       employee.type AS type,employee.level AS level, employee.photo_link AS photoLink,\n" +
            "                       array_agg(employee_list_file.list_file) AS listFile\n" +
            "                       FROM employee\n" +
            "                       INNER JOIN direction ON employee.direction_id = direction.id\n" +
            "                       INNER JOIN position ON employee.position_id = position.id\n" +
            "                      INNER JOIN department ON direction.department_id = department.id\n" +
            "                      LEFT JOIN employee_list_file ON employee.id = employee_list_file.employee_id where employee.id =:employeeId\n" +
            "                       GROUP BY employee.id, position.id, direction.id, department.id",nativeQuery = true)
    Optional<EmployeeIdProjection> getfindById(@Param("employeeId") Long employeeId);
    @Query(value = "SELECT employee.id AS id, employee.full_name AS fullName, employee.about AS about,\n" +
            "          employee.editor AS editor, position.id AS position_id, position.name AS position_name,\n" +
            "           direction.id AS direction_id, direction.name AS direction_name,\n" +
            "            department.id AS department_id, department.name AS department_name,\n" +
            "            employee.type AS type,employee.level AS level, employee.photo_link AS photoLink,\n" +
            "            array_agg(employee_list_file.list_file) AS listFile\n" +
            "            FROM employee\n" +
            "            INNER JOIN direction ON employee.direction_id = direction.id\n" +
            "            INNER JOIN position ON employee.position_id = position.id\n" +
            "           INNER JOIN department ON direction.department_id = department.id\n" +
            "           LEFT JOIN employee_list_file ON employee.id = employee_list_file.employee_id where employee.type =:employeeType\n" +
            "            GROUP BY employee.id, position.id, direction.id, department.id order by employee.level asc;",nativeQuery = true)
    List<EmployeeIdProjection> findAllByType(@Param("employeeType") Integer employeeType);

    @Query(value = "SELECT employee.id AS id, employee.full_name AS fullName, employee.about AS about,\n" +
            "                 employee.editor AS editor, position.id AS position_id, position.name AS position_name,\n" +
            "                       direction.id AS direction_id, direction.name AS direction_name,\n" +
            "                        department.id AS department_id, department.name AS department_name,\n" +
            "                        employee.type AS type, employee.level AS level,employee.photo_link AS photoLink,\n" +
            "                        array_agg(employee_list_file.list_file) AS listFile\n" +
            "                        FROM employee\n" +
            "                        INNER JOIN direction ON employee.direction_id = direction.id\n" +
            "                        INNER JOIN position ON employee.position_id = position.id\n" +
            "                       INNER JOIN department ON direction.department_id = department.id\n" +
            "                       LEFT JOIN employee_list_file ON employee.id = employee_list_file.employee_id where direction.id =:directionId\n" +
            "                    GROUP BY employee.id, position.id, direction.id, department.id order by employee.level;",nativeQuery = true)
    List<EmployeeLevelProjection> findByDirection(@Param("directionId") Long directionId);
    @Query(value = "SELECT employee.id AS id, employee.full_name AS fullName, employee.about AS about,\n" +
            "       position.id AS position_id, position.name AS position_name,\n" +
            "       direction.id AS direction_id, direction.name AS direction_name,\n" +
            "       department.id AS department_id, department.name AS department_name,\n" +
            "       employee.type AS type,employee.level AS level, employee.photo_link AS photoLink,\n" +
            "       array_agg(employee_list_file.list_file) AS listFile\n" +
            "FROM employee\n" +
            "INNER JOIN direction ON employee.direction_id = direction.id\n" +
            "INNER JOIN position ON employee.position_id = position.id\n" +
            "INNER JOIN department ON direction.department_id = department.id\n" +
            "LEFT JOIN employee_list_file ON employee.id = employee_list_file.employee_id\n" +
            "GROUP BY employee.id, position.id, direction.id, department.id order by employee.level ;",nativeQuery = true)
    List<EmployeeProjection> getEmployeeProjection();


}
