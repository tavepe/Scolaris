package com.tavepe.Scolaris.repository;


import com.tavepe.Scolaris.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    List<UserEntity> findByType(String type);
}