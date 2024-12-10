package com.tavepe.Scolaris.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
@Table(name = "grades")
public class GradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private double grade;
    private String message;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private TestEntity testEntity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private UserEntity studentEntity;

}
