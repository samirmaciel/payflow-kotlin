package com.samirmaciel.payflow_kotlin.modules.bottomsheetdialog_statiment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.modules.mystatiments.MyStatimentsViewModel
import kotlinx.android.synthetic.main.bottomsheet_statiment.*

class BottomSheetDialogStatiment : BottomSheetDialogFragment() {

    private val viewModelStatiment : MyStatimentsViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottomsheet_statiment, container, false)
    }

    override fun onStart() {
        super.onStart()

        val statimentName = arguments?.get("name")
        val statimentID = arguments?.get("id").toString().toLong()

        buttonDeleteStatiment.setOnClickListener{
            val alert = AlertDialog.Builder(requireContext())

            alert.apply {
                setMessage("Deseja deletar o boleto $statimentName?")
                setPositiveButton("Sim", DialogInterface.OnClickListener{
                    dialog, id ->  viewModelStatiment.deleteById(statimentID)
                    viewModelStatiment.updateStatimentList()
                    Toast.makeText(it.context, "Boleto deletado com sucesso!", Toast.LENGTH_SHORT).show()
                    this@BottomSheetDialogStatiment.dismiss()
                })
                setNegativeButton("Não", DialogInterface.OnClickListener{
                    dialog, id -> dismiss()
                    this@BottomSheetDialogStatiment.dismiss()
                })
            }

            alert.create().show()
        }
    }
}