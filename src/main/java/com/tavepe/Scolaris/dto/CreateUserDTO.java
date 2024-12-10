package com.tavepe.Scolaris.dto;

import lombok.Data;

@Data
public class CreateUserDTO {

    private String user;
    private String type;
    private String password;
    private String name;

}