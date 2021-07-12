package com.samirmaciel.payflow_kotlin.shared.model.datarepository

import com.samirmaciel.payflow_kotlin.shared.model.paymentslip.PaymentSlip

interface PaymentSlipRepository {

    suspend fun save(registrationViewParams : RegistrationViewParams)

    fun findById(id: Long) : PaymentSlip

    suspend fun findAll() : List<PaymentSlip>

    fun deleteById(id : Long)


}