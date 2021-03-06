package com.samirmaciel.payflow_kotlin.modules.mypayments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.modules.bottomsheetdialog_payment.BottomSheetDialogPayment
import com.samirmaciel.payflow_kotlin.shared.common.PaymentsRecyclerViewAdapter
import com.samirmaciel.payflow_kotlin.shared.data.AppDataBase
import com.samirmaciel.payflow_kotlin.shared.data.PaymentSlipDataSource
import kotlinx.android.synthetic.main.my_payments_slips_fragment.*


class MyPaymentsSlipsFragment : Fragment(){

    private lateinit var paymentsAdapter : PaymentsRecyclerViewAdapter


    companion object {
        fun newInstance() =
            MyPaymentsSlipsFragment()
    }

    private val viewModel: MyPaymentsSlipsViewModel by activityViewModels({
        MyPaymentsSlipsViewModel.PaymenteViewModelFactory(PaymentSlipDataSource(AppDataBase.getDatabase(requireContext()).PaymentSlipDao()))
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.my_payments_slips_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()

    }

    override fun onStart() {
        super.onStart()


        viewModel.paymentslipList.observe(this) { list ->
            if(list.size > 0){
                textMessageStart.visibility = View.GONE
            }else{
                textMessageStart.visibility = View.VISIBLE
            }
            paymentsAdapter.setItemList(list)
            paymentsAdapter.notifyDataSetChanged()
        }

    }


    private fun initRecyclerView(){

        this.paymentsAdapter = PaymentsRecyclerViewAdapter{ paymentSlip ->
            val bottomSheet = BottomSheetDialogPayment()
            val bundle = Bundle()
            bundle.putString("name", paymentSlip.name)
            bundle.putString("value", paymentSlip.value)
            bundle.putString("barcode", paymentSlip.barcode)
            bundle.putLong("id", paymentSlip.id)
            bundle.putInt("listSize", paymentsAdapter.getItemList().size)
            bottomSheet.arguments = bundle

            bottomSheet.show(childFragmentManager, "BottomSheetPayments")

        }
        recyclerViewPayments.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewPayments.adapter = this.paymentsAdapter

    }



}