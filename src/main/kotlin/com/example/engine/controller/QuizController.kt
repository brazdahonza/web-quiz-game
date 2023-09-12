package com.example.engine.controller

import com.example.engine.dto.AnsweredQuestionResponse
import com.example.engine.dto.CreateQuestionRequestDto
import com.example.engine.dto.CreatedQuestionResponse
import com.example.engine.exception.QuestionNotFoundException
import com.example.engine.model.Question
import com.example.engine.model.Quiz
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class QuizController(var quiz: Quiz) {

    @GetMapping("/quizzes/{id}")
    fun answerQuestion(@PathVariable id: Int): Question {
        return quiz.questions.find { it.id == id }
            ?: throw QuestionNotFoundException("Question with this id does not exist")
    }

    @PostMapping("/quizzes")
    fun addQuestion(@RequestBody question: CreateQuestionRequestDto): CreatedQuestionResponse {
        val createdQuestion = Question(question.title, question.text, question.options, question.answer)
        println(question.answer)
        quiz.questions.add(createdQuestion)
        return CreatedQuestionResponse(
            createdQuestion.id,
            createdQuestion.title,
            createdQuestion.text,
            createdQuestion.options
        )
    }

    @GetMapping("/quizzes")
    fun showAllQuestions(): MutableList<Question> {
        return quiz.questions
    }

    @PostMapping("/quizzes/{id}/solve")
    fun solveQuestion(@PathVariable id: Int, @RequestParam answer: Int): AnsweredQuestionResponse {
        quiz.questions.forEach { question: Question -> println(question.correctAnswer) }
        val question = quiz.questions.find { it.id == id }
            ?: throw QuestionNotFoundException("Question with this id does not exist")
        return if(question.correctAnswer == answer) {
            AnsweredQuestionResponse(true, "Congratulations, you're right!")
        } else {
            AnsweredQuestionResponse(false, "Wrong answer! Please, try again.")
        }
    }
}