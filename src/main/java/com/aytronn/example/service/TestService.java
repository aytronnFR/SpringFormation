package com.aytronn.example.service;

import com.aytronn.example.dao.Test;
import com.aytronn.example.dto.CreateTestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TestService {

  private final List<Test> tests;

  public TestService() {
    this.tests = new ArrayList<>();
  }

  public List<Test> getAllTest() {
    return tests;
  }

  public ResponseEntity<String> getTestById(String id) {
    return null;
  }

  public Test createTest(CreateTestDto createTestDto) {
    Test test = new Test(
        UUID.randomUUID(),
        createTestDto.name(),
        createTestDto.description(),
        createTestDto.age()
    );

    // Save test to database
    tests.add(test);
    return test;
  }

  public ResponseEntity<Object> updateTest(CreateTestDto createTestDto, String id) {
    return null;
  }

  public ResponseEntity<Object> deleteTest(String id) {
    return null;
  }
}
