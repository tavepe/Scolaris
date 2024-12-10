package com.tavepe.Scolaris.service;

import com.tavepe.Scolaris.dto.GradeDTO;
import com.tavepe.Scolaris.model.GradeEntity;
import com.tavepe.Scolaris.model.TestEntity;
import com.tavepe.Scolaris.model.UserEntity;
import com.tavepe.Scolaris.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final UserService userService;
    private final TestService testService;

    @Autowired
    public GradeService(UserService userService, TestService testService, GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
        this.userService = userService;
        this.testService = testService;
    }

    public GradeEntity createGrade(GradeEntity grade) {
        grade.setId(null);
        return gradeRepository.save(grade);
    }

    private GradeEntity getGradeByStudent(Integer studentId, Integer testId) {

        UserEntity student = userService.getUserById(studentId);
        TestEntity test = testService.getTestById(testId);


        return gradeRepository.findByStudentEntityAndTestEntity(student, test);


    }
    private GradeEntity getGradeById(Integer id) {
        return gradeRepository.findById(id).orElse(null);
    }
    public GradeDTO getGradeDTOByStudent(Integer studentId, Integer testId) {
        GradeEntity gradeEntity = getGradeByStudent(studentId, testId);
        GradeDTO gradeDTO = new GradeDTO();
        gradeDTO.setId(gradeEntity.getId());
        gradeDTO.setGrade(gradeEntity.getGrade());
        gradeDTO.setMessage(gradeEntity.getMessage());
        return gradeDTO;
    }
    public GradeDTO getGradeDTOById(Integer id) {
        GradeEntity gradeEntity = getGradeById(id);
        GradeDTO gradeDTO = new GradeDTO();
        gradeDTO.setId(gradeEntity.getId());
        gradeDTO.setGrade(gradeEntity.getGrade());
        gradeDTO.setMessage(gradeEntity.getMessage());
        return gradeDTO;
    }
}
