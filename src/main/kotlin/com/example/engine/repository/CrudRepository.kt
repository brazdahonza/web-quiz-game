package com.example.engine.repository

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository
import java.util.*

@NoRepositoryBean
interface CrudRepository<T, ID> : Repository<T, ID> {
    // CREATE/UPDATE methods
    fun <S : T> save(entity: S): S
    fun <S : T> saveAll(entities: Iterable<S>): Iterable<S>

    // READ methods
    fun findById(id: ID): Optional<T>
    fun existsById(id: ID): Boolean
    fun findAll(): Iterable<T>
    fun findAllById(ids: Iterable<ID>): Iterable<T>
    fun count(): Long

    // DELETE methods
    fun deleteById(id: ID)
    fun delete(entity: T)
    fun deleteAllById(ids: Iterable<ID>)
    fun deleteAll(entities: Iterable<T>)
    fun deleteAll()
}