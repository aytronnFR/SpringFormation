package com.aytronn.example.controller;

import com.aytronn.example.dao.Test;
import com.aytronn.example.dto.CreateTestDto;
import com.aytronn.example.service.TestService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

  private final TestService testService;

  public TestController(TestService testService) {
    this.testService = testService;
  }

  @GetMapping
  public ResponseEntity<List<Test>> getTest() {
    List<Test> allTest = testService.getAllTest();
    return ResponseEntity.ok(allTest);
  }

  @GetMapping("/{id}")
  public ResponseEntity<String> getTestById(
      @PathVariable String id,
      @RequestParam String parameterOptional
  ) {
    return testService.getTestById(id);
  }

  @PostMapping
  public ResponseEntity<Test> createTest(@RequestBody @Valid CreateTestDto createTestDto) {
    Test test = testService.createTest(createTestDto);
    return ResponseEntity.ok(test);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateTest(
      @RequestBody CreateTestDto createTestDto,
      @PathVariable String id
  ) {
    return testService.updateTest(createTestDto, id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteTest(
      @PathVariable String id
  ) {
    return testService.deleteTest(id);
  }
}
