package com.gabriel.kotlin.core.mapper

import com.gabriel.kotlin.core.model.PersonModel
import com.gabriel.kotlin.infrastructure.entity.PersonEntity

object PersonCoreMapper {
    fun toModel(entity: PersonEntity): PersonModel {
        return PersonModel(
            id = entity.id,
            name = entity.name,
            age = entity.age
        )
    }

    fun toEntity(model: PersonModel): PersonEntity {
        return PersonEntity(
            name = model.name,
            age = model.age
        )
    }
}