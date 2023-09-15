package com.example.engine.controller

import com.example.engine.Runner
import com.example.engine.dto.AnswerRequest
import com.example.engine.dto.AnsweredQuestionResponse
import com.example.engine.dto.CreateQuestionRequestDto
import com.example.engine.dto.CreatedQuestionResponse
import com.example.engine.exception.QuestionNotFoundException
import com.example.engine.model.Question
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class QuizController(val runner: Runner) {

    @GetMapping("/quizzes/{id}")
    fun answerQuestion(@PathVariable id: Long): Question {
        return runner.findById(id).orElseThrow { QuestionNotFoundException("Question with this id does not exist") }
    }

    @PostMapping("/quizzes")
    fun addQuestion(@RequestBody @Valid question: CreateQuestionRequestDto): CreatedQuestionResponse {
        val createdQuestion = Question(question.title, question.text, question.options, question.answer)
        runner.add(createdQuestion)
        return CreatedQuestionResponse(
            createdQuestion.title,
            createdQuestion.text,
            createdQuestion.options,
            createdQuestion.id
        )
    }

    @GetMapping("/quizzes")
    fun showAllQuestions(): MutableList<Question> {
        return runner.getAll().toMutableList()
    }

    @PostMapping("/quizzes/{id}/solve")
    fun solveQuestion(@PathVariable id: Long, @RequestBody answerRequest: AnswerRequest): AnsweredQuestionResponse {
        val question = runner.findById(id).orElseThrow { QuestionNotFoundException("Question with this id does not exist") }
        val userAnswers = answerRequest.answer
        if(question.correctAnswer.size == 0 && userAnswers.size == 0)
            return AnsweredQuestionResponse(true, "Congratulations, you're right!")

        for(correctAnswer in question.correctAnswer) {
            if(!userAnswers.contains(correctAnswer))
                return AnsweredQuestionResponse(false, "Wrong answer! Please, try again.")
        }

        if (userAnswers.size > question.correctAnswer.size)
            return AnsweredQuestionResponse(false, "Wrong answer! Please, try again.")

        return AnsweredQuestionResponse(true, "Congratulations, you're right!")
    }

    @DeleteMapping("/quizzes")
    fun deleteAll() {
        runner.deleteAll()
    }
}