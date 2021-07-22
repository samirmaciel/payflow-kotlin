package com.samirmaciel.payflow_kotlin.modules.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.PaymentSlipRepository
import com.samirmaciel.payflow_kotlin.shared.model.paymentslip.PaymentSlip
import kotlinx.coroutines.launch

class HomeViewModel(private val paymentSlipRepository: PaymentSlipRepository): ViewModel() {

    val paymentslipList = MutableLiveData<MutableList<PaymentSlip>>()
    var index = 0



    init {
        findAllPaymentSlip()
    }


    fun findAllPaymentSlip(){
        viewModelScope.launch {
            paymentslipList.postValue((paymentSlipRepository.findAll()))
        }

    }


    class HomeViewModelFactory(private val paymentSlipRepository: PaymentSlipRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(paymentSlipRepository) as T
        }


    }


}