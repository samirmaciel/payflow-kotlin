package com.samirmaciel.payflow_kotlin.modules.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.PaymentSlipRepository
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.RegistrationViewParams
import com.samirmaciel.payflow_kotlin.shared.model.paymentslip.PaymentSlip
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val paymentSlipRepository : PaymentSlipRepository
) : ViewModel() {


    fun savePaymentSlip(registrationViewParams: RegistrationViewParams){
        viewModelScope.launch {
            paymentSlipRepository.save(registrationViewParams)
        }

    }

    fun findAllPaymentSlip() : List<PaymentSlip>{
        return paymentSlipRepository.findAll()
    }


    class RegistrationViewModelFactory(private val paymentSlipRepository : PaymentSlipRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RegistrationViewModel(paymentSlipRepository) as T
        }

    }
}