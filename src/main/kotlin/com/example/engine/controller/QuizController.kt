package com.example.engine.controller

import com.example.engine.dto.AnswerRequest
import com.example.engine.dto.AnsweredQuestionResponse
import com.example.engine.dto.CreateQuestionRequestDto
import com.example.engine.dto.CreatedQuestionResponse
import com.example.engine.exception.QuestionNotFoundException
import com.example.engine.model.Question
import com.example.engine.model.Quiz

import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api")
class QuizController(var quiz: Quiz) {

    @GetMapping("/quizzes/{id}")
    fun answerQuestion(@PathVariable id: Int): Question {
        return quiz.questions.find { it.id == id }
            ?: throw QuestionNotFoundException("Question with this id does not exist")
    }

    @PostMapping("/quizzes")
    fun addQuestion(@RequestBody @Valid question: CreateQuestionRequestDto): CreatedQuestionResponse {
        val createdQuestion = Question(question.title, question.text, question.options, question.answer)
        println(question.answer)
        println(question.title)
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
    fun solveQuestion(@PathVariable id: Int, @RequestBody answerRequest: AnswerRequest): AnsweredQuestionResponse {
        val question = quiz.questions.find { it.id == id }
            ?: throw QuestionNotFoundException("Question with this id does not exist")

        val userAnswers = answerRequest.answer

        for(correctAnswer in question.correctAnswer) {
            if(!userAnswers.contains(correctAnswer))
                return AnsweredQuestionResponse(false, "Wrong answer! Please, try again.")
        }

        if (userAnswers.size > question.correctAnswer.size)
            return AnsweredQuestionResponse(false, "Wrong answer! Please, try again.")

        return AnsweredQuestionResponse(true, "Congratulations, you're right!")
    }
}