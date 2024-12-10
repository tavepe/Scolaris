package com.tavepe.Scolaris.repository;


import com.tavepe.Scolaris.model.ClassEntity;
import com.tavepe.Scolaris.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity,Integer> {
    List<ClassEntity> findByTeacherEntity(UserEntity teacherEntity);
}