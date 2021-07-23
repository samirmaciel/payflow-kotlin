package com.samirmaciel.payflow_kotlin.modules.bottomsheetdialog_payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.PaymentSlipRepository
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.StatimentRepository
import com.samirmaciel.payflow_kotlin.shared.model.paymentslip.toRegistrationViewParms
import kotlinx.coroutines.launch

class BottomSheetDialogPaymentViewModel(private val paymentRepository: PaymentSlipRepository, private val statimentRepository: StatimentRepository) : ViewModel() {

    fun deleteById(id : Long){
        viewModelScope.launch {
            paymentRepository.deleteById(id)
        }
    }

    fun saveStatiment(paymentID : Long){
        viewModelScope.launch {
            statimentRepository.save(paymentRepository.findById(paymentID).toRegistrationViewParms())
        }
    }

    class BottomSheetDialogViewModelFactory(private val paymentyRepository: PaymentSlipRepository, private val statimentRepository: StatimentRepository ) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BottomSheetDialogPaymentViewModel(paymentyRepository, statimentRepository) as T
        }

    }
}