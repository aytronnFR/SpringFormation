package com.aytronn.example.dao;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Test {

  private UUID id;
  private String name;
  private String description;
  private int age;
}