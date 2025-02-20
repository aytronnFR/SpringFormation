package com.aytronn.example.service;

import com.aytronn.example.config.ExampleConfig;
import com.aytronn.example.dao.Test;
import com.aytronn.example.dao.Test.TestBuilder;
import com.aytronn.example.dto.CreateTestDto;
import com.aytronn.example.exception.TestNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TestService {

  private final List<Test> tests;
  private final ExampleConfig exampleConfig;

  public TestService(ExampleConfig exampleConfig) {
    this.exampleConfig = exampleConfig;
    this.tests = new ArrayList<>();
  }

  public List<Test> getAllTest() {
    //TODO: FIND ALL OBJECT ON DATABASE

    System.out.println(exampleConfig.getValue());
    return tests;
  }

  public Test getTestById(UUID id) {
    //TODO: FIND OBJECT FROM DATABASE WITH FILTER ON ID
    Optional<Test> first = tests.stream()
        .filter(test -> test.getId().equals(id))
        .findFirst();

    if (first.isEmpty()) {
      throw new TestNotFoundException("Test not found");
    }
    return first.get();
  }

  public Test createTest(CreateTestDto createTestDto) {
    Test test = new Test(
        UUID.randomUUID(),
        createTestDto.name(),
        createTestDto.description(),
        createTestDto.age()
    );

    TestBuilder builder = Test.builder()
        .name(createTestDto.name())
        .age(createTestDto.age());
    if (createTestDto.description() != null) {
      builder.description(createTestDto.description());
    }

    Test build = builder.build();

    //TODO: SAVE OBJECT TO DATABASE
    tests.add(test);
    return test;
  }

  public Test updateTest(CreateTestDto createTestDto, UUID id) {
    Test testById = tests.stream().filter(test -> test.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new TestNotFoundException("Test not found"));

    testById.setName(createTestDto.name());
    testById.setDescription(createTestDto.description());
    testById.setAge(createTestDto.age());
    //TODO: SAVE TO DATABASE
    return testById;
  }

  public Test deleteTest(UUID id) {
    Test testById = tests.stream().filter(test -> test.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new TestNotFoundException("Test not found"));

    //TODO: REMOVE OBJECT TO DATABASE
    tests.remove(testById);

    return testById;
  }
}
