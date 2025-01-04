package com.gabriel.kotlin.core.ports.`in`.service

import com.gabriel.kotlin.core.model.PersonModel

interface IPersonServicePort {
    fun createPerson(personModel: PersonModel): PersonModel
    fun getAllPeople() : List<PersonModel>
}