package com.example.engine.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class CreateQuestionRequestDto(
    @NotBlank val title: String,
    @NotBlank val text: String,
    @Size(min=2) val options: MutableList<String>,
    @Size(min=0) val answer: MutableList<Int>
)