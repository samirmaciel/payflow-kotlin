package com.samirmaciel.payflow_kotlin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MyStatimentsFragment : Fragment() {

    companion object {
        fun newInstance() = MyStatimentsFragment()
    }

    private lateinit var viewModel: MyStatimentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_statiments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyStatimentsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}