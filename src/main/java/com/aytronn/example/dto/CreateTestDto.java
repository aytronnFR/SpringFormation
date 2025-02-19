package com.aytronn.example.dto;

import jakarta.validation.constraints.NotNull;

public record CreateTestDto(
    @NotNull String name,
    String description,
    int age
) {

}
