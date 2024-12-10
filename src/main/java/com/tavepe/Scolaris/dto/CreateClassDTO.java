package com.tavepe.Scolaris.dto;

import lombok.Data;
import java.util.Set;

@Data
public class CreateClassDTO {
    private String title;
    private String description;
    private Integer teacherId;
    Set<Integer> studentsIds;

}
