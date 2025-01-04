package com.gabriel.kotlin.entrypoint.controller

import com.gabriel.kotlin.core.adapter.service.PersonServiceAdapter
import com.gabriel.kotlin.entrypoint.dto.request.PersonRequestDTO
import com.gabriel.kotlin.entrypoint.dto.response.PersonResponseDTO
import com.gabriel.kotlin.entrypoint.mapper.PersonEntrypointMapper
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/persons")
class PersonController(private val personService: PersonServiceAdapter) {

    @GetMapping
    fun getAllPersons(): List<PersonResponseDTO> {
        val persons = personService.getAllPeople()
        return persons.map {
            PersonEntrypointMapper.toResponse(it)
        }
    }

    @PostMapping
    fun createPerson(@Valid @RequestBody personRequestDTO: PersonRequestDTO): PersonResponseDTO {

        val createPerson = personService.createPerson(PersonEntrypointMapper.toModel(personRequestDTO))
        return PersonEntrypointMapper.toResponse(createPerson)
    }
}