package com.samirmaciel.payflow_kotlin.modules.bottomsheetdialog_payment

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.modules.home.HomeViewModel
import com.samirmaciel.payflow_kotlin.modules.mypayments.MyPaymentsSlipsViewModel
import com.samirmaciel.payflow_kotlin.shared.data.AppDataBase
import com.samirmaciel.payflow_kotlin.shared.data.PaymentSlipDataSource
import com.samirmaciel.payflow_kotlin.shared.data.StatimentDataSource
import kotlinx.android.synthetic.main.bottomsheet_payment.*

class BottomSheetDialogPayment : BottomSheetDialogFragment() {

    private val viewModelHome : HomeViewModel by activityViewModels()
    private val viewModelPayments : MyPaymentsSlipsViewModel by activityViewModels()
    private val paymentViewModel : BottomSheetDialogPaymentViewModel by activityViewModels({
        BottomSheetDialogPaymentViewModel.BottomSheetDialogViewModelFactory(PaymentSlipDataSource(AppDataBase.getDatabase(requireContext()).PaymentSlipDao()), StatimentDataSource(AppDataBase.getDatabase(requireContext()).StatimentDao()))
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.bottomsheet_payment, container, false)
    }


    override fun onStart() {
        super.onStart()


        val name = arguments?.get("name")
        val value = arguments?.get("value")
        val id = arguments?.get("id").toString().toLong()

        var text = "O Boleto <b>$name</b> no valor de <b>$value</b> já foi pago?"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            textTitle.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
        }

        buttonYes.setOnClickListener{
            paymentViewModel.saveStatiment(id)
            viewModelHome.findAllPaymentSlip()
            viewModelPayments.deleteById(id)
            dismiss()
        }
        buttonNotYet.setOnClickListener{
            dismiss()
        }

        buttonDeletePayment.setOnClickListener{
            viewModelPayments.deleteById(id)
            viewModelHome.findAllPaymentSlip()
            dismiss()
        }

    }




}