package com.aytronn.example.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ExampleConfig {

  @Value("${example.config.value}")
  private String value;
}
