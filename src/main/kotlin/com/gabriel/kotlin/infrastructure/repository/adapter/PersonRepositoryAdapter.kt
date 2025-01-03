package com.gabriel.kotlin.infrastructure.repository.adapter

import com.gabriel.kotlin.core.ports.out.repository.IPersonRepositoryPort
import com.gabriel.kotlin.infrastructure.entity.PersonEntity
import com.gabriel.kotlin.infrastructure.repository.PersonRepository
import org.springframework.stereotype.Component

@Component
class PersonRepositoryAdapter(private val personRepository: PersonRepository) : IPersonRepositoryPort {
    override fun savePerson(name: String, age: Int): PersonEntity {
        val personEntity = PersonEntity(name = name, age = age)
        return personRepository.save(personEntity)
    }

    override fun getAllPeople(): List<PersonEntity> {
        return personRepository.findAll()
    }
}