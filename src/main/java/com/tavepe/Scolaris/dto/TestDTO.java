package com.tavepe.Scolaris.dto;

import com.tavepe.Scolaris.model.ClassEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class TestDTO {

    private Integer id;
    private String testTitle;
    private double maxTestGrade;
    private String testDescription;
    private String classTitle;
}
