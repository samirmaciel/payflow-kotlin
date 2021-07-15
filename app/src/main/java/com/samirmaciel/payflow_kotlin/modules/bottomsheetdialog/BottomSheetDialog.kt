package com.samirmaciel.payflow_kotlin.modules.bottomsheetdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.modules.home.HomeViewModel
import com.samirmaciel.payflow_kotlin.modules.mypayments.MyPaymentsSlipsViewModel
import com.samirmaciel.payflow_kotlin.shared.data.AppDataBase
import com.samirmaciel.payflow_kotlin.shared.data.PaymentSlipDataSource
import com.samirmaciel.payflow_kotlin.shared.data.StatimentDataSource
import kotlinx.android.synthetic.main.bottomsheet.*

class BottomSheetDialog : BottomSheetDialogFragment() {

    private val homeViewModel : HomeViewModel by activityViewModels()
    private val viewModelPayments : MyPaymentsSlipsViewModel by activityViewModels()
    private val viewModel : BottomSheetDialogViewModel by activityViewModels({
        BottomSheetDialogViewModel.BottomSheetDialogViewModelFactory(PaymentSlipDataSource(AppDataBase.getDatabase(requireContext()).PaymentSlipDao()), StatimentDataSource(AppDataBase.getDatabase(requireContext()).StatimentDao()))
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.bottomsheet, container, false)
    }

    override fun onStart() {
        super.onStart()


        val name = arguments?.get("name")
        val value = arguments?.get("value")
        val id = arguments?.get("id").toString().toLong()

        textTitle.text = "O Boleto $name no valor de $value já foi pago?"


        buttonYes.setOnClickListener{
            viewModel.saveStatiment(id)
            viewModelPayments.deleteById(id)
            dismiss()
        }
        buttonNotYet.setOnClickListener{
            dismiss()
        }

        buttonDelete.setOnClickListener{
            viewModelPayments.deleteById(id)
            homeViewModel.findAllPaymentSlip()
            dismiss()
        }

    }




}