package com.tavepe.Scolaris.dto;
import lombok.Data;

@Data
public class CreateGradeDTO {
    private double grade;
    private String message;
    private Integer testId;
    private Integer studentId;
}
