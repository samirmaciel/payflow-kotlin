package com.samirmaciel.payflow_kotlin.modules.mypayments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.modules.bottomsheetdialog.BottomSheetDialog
import com.samirmaciel.payflow_kotlin.shared.common.ParamsRecyclerViewAdapter
import com.samirmaciel.payflow_kotlin.shared.data.AppDataBase
import com.samirmaciel.payflow_kotlin.shared.data.PaymentSlipDataSource
import kotlinx.android.synthetic.main.my_payments_slips_fragment.*
import kotlin.math.log10

class MyPaymentsSlipsFragment : Fragment(){

    private lateinit var paramsAdapter : ParamsRecyclerViewAdapter


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

        //viewModel = ViewModelProvider(this, MyPaymentsSlipsViewModel.PaymenteViewModelFactory(PaymentSlipDataSource(AppDataBase.getDatabase(requireContext()).PaymentSlipDao()))).get(MyPaymentsSlipsViewModel::class.java)

        initRecyclerView()
        viewModel.findAllPaymentSlip()

    }

    override fun onStart() {
        super.onStart()

        viewModel.paymentslipList.observe(this, { list ->
            paramsAdapter.setPaymentList(list)
            paramsAdapter.notifyDataSetChanged()
        })

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.d("HIDDEN", "onHiddenChanged: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("PAUSE", "onPause: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("RESUME", "onResume: ")
    }




    private fun initRecyclerView(){

        this.paramsAdapter = ParamsRecyclerViewAdapter{paymentSlip ->
            val bottomSheet = BottomSheetDialog()
            val bundle = Bundle()
            bundle.putString("name", paymentSlip.name)
            bundle.putString("value", paymentSlip.value)
            bundle.putLong("id", paymentSlip.id)
            bottomSheet.arguments = bundle

            bottomSheet.show(childFragmentManager, "BottomSheetDialog")

        }
        recyclerViewPayments.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewPayments.adapter = this.paramsAdapter

    }



}