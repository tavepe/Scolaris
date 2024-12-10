package com.tavepe.Scolaris.repository;


import com.tavepe.Scolaris.model.GradeEntity;
import com.tavepe.Scolaris.model.TestEntity;
import com.tavepe.Scolaris.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity,Integer> {
    GradeEntity findByStudentEntityAndTestEntity(UserEntity student, TestEntity test);
}