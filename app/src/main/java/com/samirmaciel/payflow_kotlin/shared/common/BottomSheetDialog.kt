package com.samirmaciel.payflow_kotlin.shared.common

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.modules.mypayments.MyPaymentsSlipsViewModel
import com.samirmaciel.payflow_kotlin.shared.data.AppDataBase
import com.samirmaciel.payflow_kotlin.shared.data.PaymentSlipDataSource
import kotlinx.android.synthetic.main.bottomsheet.*
import java.lang.ClassCastException
import java.lang.RuntimeException

class BottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var viewModel : MyPaymentsSlipsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.bottomsheet, container, false)
    }

    override fun onStart() {
        super.onStart()

        viewModel = ViewModelProvider(this, MyPaymentsSlipsViewModel.PaymenteViewModelFactory(PaymentSlipDataSource(AppDataBase.getDatabase(requireContext()).PaymentSlipDao()))).get(MyPaymentsSlipsViewModel::class.java)

        val name = arguments?.get("name")
        val value = arguments?.get("value")
        val id = arguments?.get("id")

        textTitle.text = "O Boleto $name no valor de $value já foi pago?"


        buttonYes.setOnClickListener{
            viewModel.test()
            dismiss()
        }
        buttonNotYet.setOnClickListener{
            dismiss()
        }

        buttonDelete.setOnClickListener{
            dismiss()
        }

    }




}