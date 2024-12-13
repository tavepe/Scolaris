package com.tavepe.Scolaris.controller;


import java.util.List;

import com.tavepe.Scolaris.dto.*;
import com.tavepe.Scolaris.model.ClassEntity;
import com.tavepe.Scolaris.model.GradeEntity;
import com.tavepe.Scolaris.model.TestEntity;
import com.tavepe.Scolaris.model.UserEntity;
import com.tavepe.Scolaris.service.ClassService;
import com.tavepe.Scolaris.service.GradeService;
import com.tavepe.Scolaris.service.TestService;
import com.tavepe.Scolaris.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/data")
public class DataController {

    private final ClassService classService;
    private final UserService userService;
    private final TestService testService;
    private final GradeService gradeService;

    @Autowired
    public DataController(ClassService classService, UserService userService, TestService testService, GradeService gradeService) {
        this.classService = classService;
        this.userService = userService;
        this.testService = testService;
        this.gradeService = gradeService;
    }

    @GetMapping("/classes")
    public ResponseEntity<List<ClassDTO>> getAllClasses() {
        List<ClassDTO> classesDTO = classService.listAllClassesDTO();
        return new ResponseEntity<>(classesDTO, HttpStatus.OK);
    }

    @GetMapping("/classes/{id}")
    public ResponseEntity<ClassDTO> getClass(@PathVariable Integer id) {
        ClassDTO classDTO=classService.getCLassDTOById(id);
        return new ResponseEntity<>(classDTO, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
        UserDTO user = userService.getUserDTOById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/grades/student/{studentId}/test/{testId}")
    public ResponseEntity<GradeDTO> getGrade(@PathVariable Integer studentId, @PathVariable Integer testId) {
        GradeDTO grade = gradeService.getGradeDTOByStudent(studentId, testId);
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @GetMapping("/tests/{id}")
    public ResponseEntity<TestDTO> getTest(@PathVariable Integer id) {
        TestDTO test = testService.getTestDTOById(id);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.listAllUsersDTO();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/classes/{id}/tests")
    public ResponseEntity<List<TestDTO>> getTestsByClass(@PathVariable Integer id) {
        List<TestDTO> tests = testService.findAllTestsDTOByClass(id);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<List<UserDTO>> getAllStudents() {
        List<UserDTO> students = userService.getUsersDTOByType("student");
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<UserDTO>> getAllTeachers() {
        List<UserDTO> teachers = userService.getUsersDTOByType("teacher");
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/data/teachers/{id}/classes")
    public ResponseEntity<List<ClassDTO>> getClassesByTeacher(@PathVariable Integer id) {

        List<ClassDTO> classes = classService.getAllClassesDTOByTeacher(id);

        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @PostMapping("/grades")
    public ResponseEntity<GradeDTO> createGrade(@RequestBody CreateGradeDTO gradeRequest) {
        GradeEntity gradeEntity = new GradeEntity();
        gradeEntity.setGrade(gradeRequest.getGrade());
        gradeEntity.setMessage(gradeRequest.getMessage());
        gradeEntity.setStudentEntity(userService.getUserById(gradeRequest.getStudentId()));
        gradeEntity.setTestEntity(testService.getTestById(gradeRequest.getTestId()));

        GradeDTO responseGradeDTO=gradeService.getGradeDTOById(gradeService.createGrade(gradeEntity).getId());
        return new ResponseEntity<>(responseGradeDTO, HttpStatus.CREATED);
    }

    @PostMapping("/tests")
    public ResponseEntity<TestDTO> createTest(@RequestBody CreateTestDTO testRequest) {
        TestEntity testEntity = new TestEntity();
        testEntity.setTestTitle(testRequest.getTestTitle());
        testEntity.setTestDescription(testRequest.getTestDescription());
        testEntity.setMaxTestGrade(testRequest.getMaxTestGrade());
        testEntity.setClassEntity(classService.getClassById(testRequest.getClassId()));

        TestDTO responseTestDTO= testService.getTestDTOById(testService.createTest(testEntity).getId()) ;
        return new ResponseEntity<>(responseTestDTO, HttpStatus.CREATED);
    }

    @PostMapping("/classes")
    public ResponseEntity<ClassDTO> createClass(@RequestBody CreateClassDTO classRequest) {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setTitle(classRequest.getTitle());
        classEntity.setDescription(classRequest.getDescription());
        classEntity.setTeacherEntity(userService.getUserById(classRequest.getTeacherId()));
        classEntity.setStudents(userService.getAllUsersById(classRequest.getStudentsIds()));

        ClassDTO responseClassDTO=classService.getCLassDTOById(classService.createClass(classEntity).getId());
        return new ResponseEntity<>(responseClassDTO, HttpStatus.CREATED);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setUser(userDTO.getUser());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setType(userDTO.getType());

        UserDTO responseUserDTO = userService.getUserDTOById(userService.createUser(user).getId());
        return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody CreateUserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setUser(userDTO.getUser());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setType(userDTO.getType());
        UserDTO updatedUser = userService.getUserDTOById(userService.updateUser(id, user).getId());
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/classes/{id}")
    public ResponseEntity<ClassDTO> updateClass(@PathVariable Integer id, @RequestBody CreateClassDTO classDTO) {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setTitle(classDTO.getTitle());
        classEntity.setDescription(classDTO.getDescription());
        classEntity.setTeacherEntity(userService.getUserById(classDTO.getTeacherId()));
        classEntity.setStudents(userService.getAllUsersById(classDTO.getStudentsIds()));
        ClassDTO classResponseDTO = classService.getCLassDTOById(classService.updateClass(id, classEntity).getId());
        return new ResponseEntity<>(classResponseDTO, HttpStatus.OK);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/classes/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Integer id) {
        classService.deleteClass(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
