package com.mike.webflux.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

public record Fruit(
    @Id @Size(min = 2, max = 5, message = "Id must be greater than 1 char and no more than 5")
        String name,
    @NotNull String colour) {}
