package com.example.engine

import com.example.engine.model.Question
import com.example.engine.repository.QuizRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.util.*

@SpringBootApplication
class WebQuizEngine

    fun main(args: Array<String>) {
        runApplication<WebQuizEngine>(*args)
    }

@Component
class Runner(private val repository: QuizRepository, private val jdbcTemplate: JdbcTemplate) : CommandLineRunner {

    override fun run(vararg args: String) {
       println("DB STARTED!")
    }

    fun findById(id: Long): Optional<Question> {
        return repository.findById(id)
    }

    fun add(question: Question) {
        repository.save(question)
    }

    fun getAll(): Iterable<Question> {
        return repository.findAll()
    }

    fun deleteAll() {
        repository.deleteAll()
        jdbcTemplate.execute("ALTER TABLE questions ALTER COLUMN id RESTART WITH 1")
    }
}
