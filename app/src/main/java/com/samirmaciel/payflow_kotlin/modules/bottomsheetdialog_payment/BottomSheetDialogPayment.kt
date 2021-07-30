package com.samirmaciel.payflow_kotlin.modules.bottomsheetdialog_payment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.app.AlertDialog
import android.content.DialogInterface
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
    private val viewModelThis: BottomSheetDialogPaymentViewModel by activityViewModels {
        BottomSheetDialogPaymentViewModel.BottomSheetDialogViewModelFactory(PaymentSlipDataSource(AppDataBase.getDatabase(requireContext()).PaymentSlipDao()), StatimentDataSource(AppDataBase.getDatabase(requireContext()).StatimentDao()))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.bottomsheet_payment, container, false)
    }


    override fun onStart() {
        super.onStart()


        val name = arguments?.get("name")
        val value = arguments?.get("value")
        val barcode = arguments?.get("barcode").toString()
        val paymentId = arguments?.get("id").toString().toLong()
        val itemListSize = arguments?.get("listSize").toString().toInt()

        var text = "O Boleto <b>$name</b> no valor de <b>$value</b> já foi pago?"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            textTitle.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
        }

        buttonYes.setOnClickListener{
            viewModelThis.saveStatiment(paymentId)
            viewModelPayments.deleteById(paymentId)
            viewModelHome.updatePaymentList()
            dismiss()
        }
        buttonNotYet.setOnClickListener{
            dismiss()
        }

        buttonDeletePayment.setOnClickListener{
            val alert = AlertDialog.Builder(requireContext())
            alert.apply {
                setTitle("Deseja excluir este boleto?")
                setPositiveButton("Sim", DialogInterface.OnClickListener { dialog, id ->
                    viewModelPayments.deleteById(paymentId)
                    viewModelHome.updatePayments(itemListSize - 1)
                    this@BottomSheetDialogPayment.dismiss()
                })
                setNegativeButton("Não", DialogInterface.OnClickListener { dialog, id ->
                    dismiss()
                    this@BottomSheetDialogPayment.dismiss()
               })
            }
            alert.create().show()

        }

        buttonCoyCode.setOnClickListener{
            copyToClipboard(barcode)
            val toast = Toast(requireContext())
            val view = layoutInflater.inflate(R.layout.custom_toast_sucessful, requireActivity().findViewById<ViewGroup>(R.id.toast_layout), false)
            view.findViewById<TextView>(R.id.textTitleToast).text = "Código de barras copiado!"
            toast.apply {
                setGravity(Gravity.TOP, 0 , 0)
                duration = Toast.LENGTH_SHORT
                setView(view)
            }
            toast.show()
            dismiss()
        }

    }

    private fun copyToClipboard(text : String){
        val clipboard = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(text, text)
        clipboard.setPrimaryClip(clip)
    }




}