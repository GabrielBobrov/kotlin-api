package com.gabriel.kotlin.entrypoint.controller

import com.gabriel.kotlin.infrastructure.entity.PersonEntity
import com.gabriel.kotlin.core.exception.PersonNotFoundException
import com.gabriel.kotlin.infrastructure.repository.PersonRepository
import com.gabriel.kotlin.entrypoint.dto.PersonRequestDTO
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/persons")
class PersonController(private val repository: PersonRepository) {

    @GetMapping
    fun getAllPersons(): List<PersonEntity> {
        val persons = repository.findAll().ifEmpty {
            throw PersonNotFoundException("No persons found")
        }
        return persons
    }

    @PostMapping
    fun createPerson(@Valid @RequestBody personRequestDTO: PersonRequestDTO): PersonEntity {

        val personEntity = PersonEntity(
            name = personRequestDTO.name,
            age = personRequestDTO.age!!
        )
        return repository.save(personEntity)
    }
}