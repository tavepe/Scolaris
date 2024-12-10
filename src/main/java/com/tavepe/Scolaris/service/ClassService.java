package com.tavepe.Scolaris.service;

import java.util.List;
import java.util.stream.Collectors;

import com.tavepe.Scolaris.dto.ClassDTO;
import com.tavepe.Scolaris.model.ClassEntity;
import com.tavepe.Scolaris.model.UserEntity;
import com.tavepe.Scolaris.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClassService {


    private final ClassRepository classRepository;
    private final UserService userService;

    @Autowired
    public ClassService(ClassRepository classRepository, UserService userService) {
        this.classRepository = classRepository;
        this.userService = userService;
    }

    public ClassEntity createClass(ClassEntity classEntity) {
        classEntity.setId(null);
        return classRepository.save(classEntity);
    }

    public ClassEntity getClassById(Integer classId) {
        return classRepository.findById(classId).orElse(null);
    }
    public ClassDTO getCLassDTOById(Integer classId) {
        ClassEntity classEntity=getClassById(classId);
        ClassDTO classDTO = new ClassDTO();
        classDTO.setId(classEntity.getId());
        classDTO.setTitle(classEntity.getTitle());
        classDTO.setDescription(classEntity.getDescription());
        classDTO.setTeacher(classEntity.getTeacherEntity().getName());
        return classDTO;
    }

    public ClassEntity updateClass(Integer classId, ClassEntity classRequest) {

        ClassEntity classEntity = getClassById(classId);
        classEntity.setTitle(classRequest.getTitle());
        classEntity.setDescription(classRequest.getDescription());
        classEntity.setTeacherEntity(classRequest.getTeacherEntity());
        classEntity.setStudents(classRequest.getStudents());
        classRepository.save(classEntity);
        return classEntity;

    }

    protected List<ClassEntity> listAllClasses() {
        return classRepository.findAll();
    }
    public List<ClassDTO>listAllClassesDTO() {
        List<ClassEntity> classes = listAllClasses();

        return getClassesDTOS(classes);
    }

    private List<ClassEntity> findAllClassesByTeacher(Integer teacherId) {
        UserEntity teacher = userService.getUserById(teacherId);
        return classRepository.findByTeacherEntity(teacher);
    }
    public List<ClassDTO> getAllClassesDTOByTeacher(Integer teacherId) {
        return getClassesDTOS(findAllClassesByTeacher(teacherId));
    }

    public void deleteClass(Integer classId) {
        ClassEntity classEntity = getClassById(classId);
        classRepository.deleteById(classEntity.getId());

    }

    private List<ClassDTO> getClassesDTOS(List<ClassEntity> classes){
        return classes.stream().map(clazz -> {
            ClassDTO classDTO = new ClassDTO();
            classDTO.setId(clazz.getId());
            classDTO.setTitle(clazz.getTitle());
            classDTO.setDescription(clazz.getDescription());
            classDTO.setTeacher(clazz.getTeacherEntity().getName());
            return classDTO;
        }).collect(Collectors.toList());
    }

}