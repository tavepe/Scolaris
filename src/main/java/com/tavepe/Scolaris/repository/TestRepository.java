package com.tavepe.Scolaris.repository;


import com.tavepe.Scolaris.model.ClassEntity;
import com.tavepe.Scolaris.model.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TestEntity,Integer> {
    List<TestEntity> findAllByClassEntity(ClassEntity classEntity);
}