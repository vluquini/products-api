package com.project.products.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
/*
Records já possuem métodos padrões de classe, como
getters, equals, hashcode, etc.
Como records são imutáveis, não possuem SETTERS.
 */
public record ProductDto(@NotBlank String name, @NotNull BigDecimal value) {
}
