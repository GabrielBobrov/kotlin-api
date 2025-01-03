package com.gabriel.kotlin.core.ports.out.repository

import com.gabriel.kotlin.infrastructure.entity.PersonEntity

interface IPersonRepositoryPort {
    fun savePerson(name: String, age: Int): PersonEntity
    fun getAllPeople(): List<PersonEntity>
}