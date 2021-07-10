package com.samirmaciel.payflow_kotlin.shared.model.datarepository

import com.samirmaciel.payflow_kotlin.shared.model.statiment.Statiment

interface StatimentRepository {

    suspend fun save(registrationViewParms : RegistrationViewParams)

    fun findById(id : Long) : Statiment

    fun findAll() : List<Statiment>

    fun deleteById(id: Long)
}