package com.samirmaciel.payflow_kotlin.shared.model.datarepository

import com.samirmaciel.payflow_kotlin.shared.model.statiment.Statiment

interface StatimentRepository {

    suspend fun save(registrationViewParms : RegistrationViewParams)

    suspend fun findById(id : Long) : Statiment

    suspend fun findAll() : List<Statiment>

    suspend fun deleteById(id: Long)
}