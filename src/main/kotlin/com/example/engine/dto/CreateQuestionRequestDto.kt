package com.example.engine.dto

import jakarta.annotation.Nullable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


class CreateQuestionRequestDto(
    @field:NotBlank val title: String,
    @field:NotBlank val text: String,
    @field:Size(min=2) val options: MutableList<String>,
    @field:Size(min=0) val answer: MutableList<Int> = mutableListOf()
)