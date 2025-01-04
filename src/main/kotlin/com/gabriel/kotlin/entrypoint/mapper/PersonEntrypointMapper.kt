package com.gabriel.kotlin.entrypoint.mapper

import com.gabriel.kotlin.core.model.PersonModel
import com.gabriel.kotlin.entrypoint.dto.request.PersonRequestDTO
import com.gabriel.kotlin.entrypoint.dto.response.PersonResponseDTO
import com.gabriel.kotlin.infrastructure.entity.PersonEntity

object PersonEntrypointMapper {

    fun toResponse(model: PersonModel): PersonResponseDTO {
        return PersonResponseDTO(
            id = model.id!!,
            name = model.name,
            age = model.age
        )
    }

    fun toModel(requestDTO: PersonRequestDTO): PersonModel {
        return PersonModel(
            name = requestDTO.name,
            age = requestDTO.age!!
        )
    }
}