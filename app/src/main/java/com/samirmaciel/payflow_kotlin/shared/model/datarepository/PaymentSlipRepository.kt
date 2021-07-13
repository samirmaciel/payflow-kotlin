package com.samirmaciel.payflow_kotlin.shared.model.datarepository

import com.samirmaciel.payflow_kotlin.shared.model.paymentslip.PaymentSlip

interface PaymentSlipRepository {

    suspend fun save(registrationViewParams : RegistrationViewParams)

    suspend fun findById(id: Long) : PaymentSlip

    suspend fun findAll() : MutableList<PaymentSlip>

    suspend fun deleteById(id : Long)


}