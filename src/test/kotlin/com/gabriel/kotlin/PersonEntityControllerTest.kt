package com.gabriel.kotlin

import com.gabriel.kotlin.core.exception.PersonNotFoundException
import com.gabriel.kotlin.entrypoint.controller.PersonController
import com.gabriel.kotlin.infrastructure.entity.PersonEntity
import com.gabriel.kotlin.infrastructure.repository.PersonRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PersonEntityControllerTest {

    @Mock
    private lateinit var repository: PersonRepository

    @InjectMocks
    private lateinit var personController: PersonController

    @Test
    fun `getAllPersons should return list of persons`() {
        val personEntities = listOf(PersonEntity(name = "John Doe", age = 30))
        `when`(repository.findAll()).thenReturn(personEntities)
        val result = personController.getAllPersons()

        assertEquals(personEntities, result)
    }

    @Test
    fun `getAllPersons should throw PersonNotFoundException when no persons found`() {
        `when`(repository.findAll()).thenReturn(emptyList())

        val exception = assertThrows(PersonNotFoundException::class.java) {
            personController.getAllPersons()
        }

        assertEquals("No persons found", exception.message)
    }
}