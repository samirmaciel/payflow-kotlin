package com.samirmaciel.payflow_kotlin.shared.model

import com.samirmaciel.payflow_kotlin.shared.model.datarepository.PaymentSlipRepository
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.RegistrationViewParams
import com.samirmaciel.payflow_kotlin.shared.model.paymentslip.*

class PaymentSlipDataSource( private val paymentSlipDao : PaymentSlipDAO ) : PaymentSlipRepository {

    override suspend fun save(registrationViewParams: RegistrationViewParams) {
        val paymentSlipEntity = registrationViewParams.toPaymentSlipEntity()
        paymentSlipDao.save(paymentSlipEntity)
    }

    override fun findById(id: Long): PaymentSlip {
        return paymentSlipDao.findById(id).toPaymentSlip()
    }

    override fun findAll(): List<PaymentSlip> {
        val paymentSlipList : List<PaymentSlipEntity> = paymentSlipDao.findAll()
        var payList : List<PaymentSlip> = listOf()
        for (pay in paymentSlipList){
            payList.toMutableList().add(pay.toPaymentSlip())
        }
        return payList
    }

    override fun deleteById(id: Long) {
        paymentSlipDao.deleteById(id)
    }
}