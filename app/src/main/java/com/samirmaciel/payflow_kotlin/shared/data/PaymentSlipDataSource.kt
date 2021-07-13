package com.samirmaciel.payflow_kotlin.shared.data

import com.samirmaciel.payflow_kotlin.shared.model.datarepository.PaymentSlipRepository
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.RegistrationViewParams
import com.samirmaciel.payflow_kotlin.shared.model.paymentslip.*

class PaymentSlipDataSource( private val paymentSlipDao : PaymentSlipDAO ) : PaymentSlipRepository {

    override suspend fun save(registrationViewParams: RegistrationViewParams) {
        val paymentSlipEntity = registrationViewParams.toPaymentSlipEntity()
        paymentSlipDao.save(paymentSlipEntity)
    }

    override suspend fun findById(id: Long): PaymentSlip {
        return paymentSlipDao.findById(id).toPaymentSlip()
    }

    override suspend fun findAll(): MutableList<PaymentSlip> {
        val paymentSlipList : List<PaymentSlipEntity> = paymentSlipDao.findAll()
        var payList : MutableList<PaymentSlip> = ArrayList()
        for (pay in paymentSlipList){
            payList.add(pay.toPaymentSlip())
        }
        return payList
    }

    override suspend fun deleteById(id: Long) {
        paymentSlipDao.deleteById(id)
    }
}