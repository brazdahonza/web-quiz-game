package com.example.engine.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.persistence.*

@Entity(name = "questions")
@JsonPropertyOrder("id", "title", "text", "options")
class Question(
    @Column(name = "title", nullable = false)
    val title: String,
    @Column(name = "text", nullable = false, length = 500)
    val text: String,
    @Column(name = "options")
    @ElementCollection(fetch = FetchType.EAGER)
    val options: MutableList<String>,
    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "answers")
    val correctAnswer: MutableList<Int>,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
