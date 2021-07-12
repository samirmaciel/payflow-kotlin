package com.samirmaciel.payflow_kotlin.modules.mypayments

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.PaymentSlipRepository
import com.samirmaciel.payflow_kotlin.shared.model.paymentslip.PaymentSlip
import kotlinx.coroutines.launch

class MyPaymentsSlipsViewModel(private val paymentSlipRepository: PaymentSlipRepository) : ViewModel() {

    val paymentslipList = MutableLiveData<List<PaymentSlip>>()


    fun findAllPaymentSlip(){
        viewModelScope.launch {
            paymentslipList.postValue((paymentSlipRepository.findAll()))
        }

    }


    class PaymenteViewModelFactory(private val paymentSlipRepository: PaymentSlipRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MyPaymentsSlipsViewModel(paymentSlipRepository) as T
        }


    }

}