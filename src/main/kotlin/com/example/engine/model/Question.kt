package com.example.engine.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.util.concurrent.atomic.AtomicInteger

@JsonPropertyOrder("id", "title", "text", "options")
class Question(
    val title: String,
    val text: String,
    val options: MutableList<String>,
    @JsonIgnore
    val correctAnswer: MutableList<Int>
) {
    val id: Int = createId()

    companion object {
        private val counter = AtomicInteger(0)

        private fun createId(): Int {
            return counter.incrementAndGet()
        }
    }
}
