package com.tavepe.Scolaris.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name="users")
public class  UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String user;
    private String type;
    private String password;
    private String name;

    @ManyToMany(mappedBy = "students")
    @JsonIgnore
    Set<ClassEntity> classes;


}


