package com.aytronn.example.dao;

import java.util.UUID;

public class Test {

  private UUID id;
  private String name;
  private String description;
  private int age;

  public Test(UUID id, String name, String description, int age) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.age = age;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
