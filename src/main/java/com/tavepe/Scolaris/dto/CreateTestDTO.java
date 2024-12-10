package com.tavepe.Scolaris.dto;
import lombok.Data;

@Data
public class CreateTestDTO {
    private String testTitle;
    private double maxTestGrade;
    private String testDescription;
    private Integer classId;
}
