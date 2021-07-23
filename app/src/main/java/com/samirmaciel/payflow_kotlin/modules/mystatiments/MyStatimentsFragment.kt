package com.samirmaciel.payflow_kotlin.modules.mystatiments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.modules.bottomsheetdialog_statiment.BottomSheetDialogStatiment
import com.samirmaciel.payflow_kotlin.shared.common.StatimentsRecyclerViewAdapter
import com.samirmaciel.payflow_kotlin.shared.data.AppDataBase
import com.samirmaciel.payflow_kotlin.shared.data.StatimentDataSource
import kotlinx.android.synthetic.main.my_statiments_fragment.*

class MyStatimentsFragment : Fragment() {

    private val viewModel : MyStatimentsViewModel by activityViewModels {
        MyStatimentsViewModel.StatimentViewModelFactory(StatimentDataSource(AppDataBase.getDatabase(requireContext()).StatimentDao()))
    }
    private lateinit var statimentsAdapter : StatimentsRecyclerViewAdapter

    companion object {
        fun newInstance() =
            MyStatimentsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_statiments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecycler()
    }

    override fun onStart() {
        super.onStart()

        viewModel.statimentList.observe(this) { list ->
            countStatiments(list.size)
            statimentsAdapter.setItemList(list)
            statimentsAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecycler() {
        this.statimentsAdapter = StatimentsRecyclerViewAdapter{
            val bottomSheet = BottomSheetDialogStatiment()
            val bundle = Bundle()
            bundle.putString("name", it.name)
            bundle.putLong("id", it.id)
            bottomSheet.arguments = bundle
            bottomSheet.show(childFragmentManager, "BottomSheetStatiments")
        }
        recyclerViewStatiments.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewStatiments.adapter = this.statimentsAdapter
    }

    private fun countStatiments(count : Int){
        textTotalStatiments.text = "$count pagos no total"
    }
}