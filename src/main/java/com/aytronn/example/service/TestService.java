package com.aytronn.example.service;

import com.aytronn.example.config.ExampleConfig;
import com.aytronn.example.dao.Test;
import com.aytronn.example.dao.Test.TestBuilder;
import com.aytronn.example.dto.CreateTestDto;
import com.aytronn.example.exception.TestNotFoundException;
import com.aytronn.example.repository.TestRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TestService {

  private final List<Test> tests;
  private final ExampleConfig exampleConfig;
  private final TestRepository testRepository;

  public TestService(ExampleConfig exampleConfig, TestRepository testRepository) {
    this.exampleConfig = exampleConfig;
    this.testRepository = testRepository;
    this.tests = new ArrayList<>();
  }

  public List<Test> getAllTest() {
    return testRepository.findAll();
  }

  public Test getTestById(UUID id) {
    Optional<Test> byId = testRepository.findById(id);

    if (byId.isEmpty()) {
      throw new TestNotFoundException("Test not found");
    }

    return byId.get();
  }

  public void createTest(CreateTestDto createTestDto) {
    Test test = Test.builder()
        .name(createTestDto.name())
        .age(createTestDto.age())
        .description(createTestDto.description())
        .build();

    testRepository.save(test);
  }

  public Test updateTest(CreateTestDto createTestDto, UUID id) {
    Optional<Test> byId = testRepository.findById(id);

    if (byId.isEmpty()) {
      throw new TestNotFoundException("Test not found");
    }

    Test test = byId.get();
    test.setName(createTestDto.name());
    test.setDescription(createTestDto.description());
    test.setAge(createTestDto.age());

    testRepository.update(test);
    return test;
  }

  public void deleteTest(UUID id) {
    Optional<Test> byId = testRepository.findById(id);

    if (byId.isEmpty()) {
      throw new TestNotFoundException("Test not found");
    }

    Test test = byId.get();

    testRepository.delete(id);
  }
}
