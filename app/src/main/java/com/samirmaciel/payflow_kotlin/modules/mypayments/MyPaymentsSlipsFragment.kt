package com.samirmaciel.payflow_kotlin.modules.mypayments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.shared.common.ParamsRecyclerViewAdapter
import com.samirmaciel.payflow_kotlin.shared.data.AppDataBase
import com.samirmaciel.payflow_kotlin.shared.data.PaymentSlipDataSource
import com.samirmaciel.payflow_kotlin.shared.model.paymentslip.PaymentSlip
import kotlinx.android.synthetic.main.my_payments_slips_fragment.*

class MyPaymentsSlipsFragment : Fragment() {

    private lateinit var paramsAdapter : ParamsRecyclerViewAdapter

    companion object {
        fun newInstance() =
            MyPaymentsSlipsFragment()
    }

    private lateinit var viewModel: MyPaymentsSlipsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.my_payments_slips_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel = ViewModelProvider(this, MyPaymentsSlipsViewModel.PaymenteViewModelFactory(PaymentSlipDataSource(AppDataBase.getDatabase(requireContext()).PaymentSlipDao()))).get(MyPaymentsSlipsViewModel::class.java)

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

    private fun initRecyclerView(){

        this.paramsAdapter = ParamsRecyclerViewAdapter()
        recyclerViewPayments.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewPayments.adapter = this.paramsAdapter

    }

}