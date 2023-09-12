package com.example.engine.controller

import com.example.engine.exception.CustomErrorMessage
import com.example.engine.exception.QuestionNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ExceptionHandlerController {

    @ExceptionHandler(QuestionNotFoundException::class)
    fun questionNotFound(e: RuntimeException, request: WebRequest) : ResponseEntity<CustomErrorMessage> {
        val body = CustomErrorMessage(
            e.message
        )
        return ResponseEntity<CustomErrorMessage>(body, HttpStatus.NOT_FOUND)
    }
}