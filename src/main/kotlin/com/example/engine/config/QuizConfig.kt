package engine.config


import engine.model.Quiz
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuizConfig {

    @Bean
    fun createQuiz(): Quiz {
        return Quiz()
    }
}