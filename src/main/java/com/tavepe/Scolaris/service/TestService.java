package com.tavepe.Scolaris.service;
import com.tavepe.Scolaris.dto.TestDTO;
import com.tavepe.Scolaris.model.ClassEntity;
import com.tavepe.Scolaris.model.TestEntity;
import com.tavepe.Scolaris.repository.TestRepository;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final ClassService classService;
    private final TestRepository testRepository;

    @Autowired
    public TestService( ClassService classService, TestRepository testRepository) {
        this.classService = classService;
        this.testRepository = testRepository;
    }

    public TestEntity createTest(TestEntity test) {
        test.setId(null);
        testRepository.save(test);
        return test;
    }

    public TestEntity getTestById(Integer id) {
        return testRepository.findById(id).orElse(null);
    }
    public TestDTO getTestDTOById(Integer id) {
        TestDTO testDTO = new TestDTO();
        TestEntity test = getTestById(id);
        testDTO.setTestTitle(test.getTestTitle());
        testDTO.setTestDescription(test.getTestDescription());
        testDTO.setMaxTestGrade(test.getMaxTestGrade());
        testDTO.setClassTitle(test.getClassEntity().getTitle());
        return testDTO;
    }

    private List<TestEntity> findAllTestsByClass(Integer classId) {
        ClassEntity classEntity = classService.getClassById(classId);
        return testRepository.findAllByClassEntity(classEntity);
    }
    public List<TestDTO> findAllTestsDTOByClass(Integer classId) {
        List<TestEntity> tests = findAllTestsByClass(classId);

        return tests.stream().map(test -> {
            TestDTO testDTO=new TestDTO();
            testDTO.setId(test.getId());
            testDTO.setTestTitle(test.getTestTitle());
            testDTO.setTestDescription(test.getTestDescription());
            testDTO.setMaxTestGrade(test.getMaxTestGrade());
            testDTO.setClassTitle(test.getClassEntity().getTitle());

            return testDTO;

        }).collect(Collectors.toList());
    }
}