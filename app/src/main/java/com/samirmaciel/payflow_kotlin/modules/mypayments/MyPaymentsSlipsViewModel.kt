package com.samirmaciel.payflow_kotlin.modules.mypayments

import android.util.Log
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

    private var index = 0


    fun findAllPaymentSlip(){
        viewModelScope.launch {
            paymentslipList.postValue((paymentSlipRepository.findAll()))
        }

    }

    fun test(){
        index++
        Log.d("VIEWMODELTEST", "test: " + index)
    }


    class PaymenteViewModelFactory(private val paymentSlipRepository: PaymentSlipRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MyPaymentsSlipsViewModel(paymentSlipRepository) as T
        }


    }

}