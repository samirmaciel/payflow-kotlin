package com.samirmaciel.payflow_kotlin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MyPaymentsSlipsFragment : Fragment() {

    companion object {
        fun newInstance() = MyPaymentsSlipsFragment()
    }

    private lateinit var viewModel: MyPaymentsSlipsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.my_payments_slips_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyPaymentsSlipsViewModel::class.java)

    }

}