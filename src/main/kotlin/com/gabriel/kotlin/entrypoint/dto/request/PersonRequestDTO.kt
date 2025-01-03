package com.gabriel.kotlin.entrypoint.dto.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class PersonRequestDTO(
    @field:NotBlank(message = "Name is mandatory")
    val name: String,

    @field:NotNull(message = "Age is mandatory")
    @field:Min(value = 0, message = "Age must be greater than or equal to 0")
    val age: Int?
)