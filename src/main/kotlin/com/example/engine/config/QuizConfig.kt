package com.example.engine.config


import com.example.engine.model.Quiz
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuizConfig {

    @Bean
    fun createQuiz(): Quiz {
        return Quiz()
    }
}