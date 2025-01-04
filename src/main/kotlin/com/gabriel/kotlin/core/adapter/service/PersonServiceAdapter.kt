package com.gabriel.kotlin.core.adapter.service

import com.gabriel.kotlin.core.exception.PersonNotFoundException
import com.gabriel.kotlin.core.mapper.PersonCoreMapper
import com.gabriel.kotlin.core.model.PersonModel
import com.gabriel.kotlin.core.ports.`in`.service.IPersonServicePort
import com.gabriel.kotlin.core.ports.out.repository.IPersonRepositoryPort
import org.springframework.stereotype.Service

@Service
class PersonServiceAdapter(private val personRepository: IPersonRepositoryPort) : IPersonServicePort {

    override fun createPerson(personModel: PersonModel): PersonModel {
        val person = personRepository.savePerson(personModel.name, personModel.age)
        return PersonCoreMapper.toModel(person)
    }

    override fun getAllPeople(): List<PersonModel> {
        val persons = personRepository.getAllPeople().ifEmpty {
            throw PersonNotFoundException("No persons found")
        }
        val personModels = persons.map { PersonCoreMapper.toModel(it) }
        return personModels
    }
}