package com.example.engine.dto

import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("id", "title", "text", "options")
class CreatedQuestionResponse(
    val title: String,
    val text: String,
    val options: MutableList<String>,
    val id: Long?
)
