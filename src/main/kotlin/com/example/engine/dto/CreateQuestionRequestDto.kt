package com.example.engine.dto

class CreateQuestionRequestDto(
    val title: String,
    val text: String,
    val options: MutableList<String>,
    val answer: Int
)