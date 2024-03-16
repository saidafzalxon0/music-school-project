package com.example.Musicschool.repository;

import com.example.Musicschool.entity.Subject;
import com.example.Musicschool.projection.SubjectDirectionGetProjection;
import com.example.Musicschool.projection.SubjectGetProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    @Query(value = "select id,name from subject where direction_id =:direction_id",nativeQuery = true)
    List<SubjectGetProjection> findAllByDirectionId(@Param("direction_id") Long direction_id);

    @Query(value = "select subject.id as id, subject.name as name, direction.id as directionId, direction.name as directionName " +
            "from subject inner join direction on subject.direction_id = direction.id where subject.id = :subject_id", nativeQuery = true)
    Optional<SubjectDirectionGetProjection> findBySubjectId(@Param("subject_id") Long id);

    Optional<Subject> findFirstByNameAndDirection_Id(String name,Long id);


}
