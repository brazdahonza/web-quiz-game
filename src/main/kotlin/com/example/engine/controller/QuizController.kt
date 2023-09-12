package engine.controller

import engine.dto.AnsweredQuestionResponse
import engine.dto.CreateQuestionRequestDto
import engine.dto.CreatedQuestionResponse
import engine.model.Question
import engine.model.Quiz
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/quizzes")
class QuizController(var quiz: Quiz) {

//    @GetMapping("/quiz")
//    fun getQuizQuestion(): MutableList<Question> {
//        return quiz.questions
//    }
//
//    @PostMapping("/quiz")
//    fun answerQuestion(@RequestParam answer: String): AnsweredQuestionResponse {
//        return AnsweredQuestionResponse(true, "pepa")
//    }

    @PostMapping
    fun addQuestion(@RequestBody question: CreateQuestionRequestDto): CreatedQuestionResponse {
        val createdQuestion = Question(question.title, question.text, question.options, question.correctAnswer)
        quiz.questions.add(createdQuestion)
        return CreatedQuestionResponse(
            createdQuestion.id,
            createdQuestion.title,
            createdQuestion.text,
            createdQuestion.options
        )
    }

}