package com.tavepe.Scolaris.model;

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
@Table(name="tests")
public class TestEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String testTitle;
    private double maxTestGrade;
    private String testDescription;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;
}
