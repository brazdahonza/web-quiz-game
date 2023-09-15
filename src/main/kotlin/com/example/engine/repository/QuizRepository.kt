package com.example.engine.repository

import com.example.engine.model.Question

interface QuizRepository : CrudRepository<Question, Long> {
}