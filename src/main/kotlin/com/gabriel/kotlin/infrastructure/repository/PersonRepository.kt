package com.gabriel.kotlin.infrastructure.repository

import com.gabriel.kotlin.infrastructure.entity.PersonEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<PersonEntity, Long>