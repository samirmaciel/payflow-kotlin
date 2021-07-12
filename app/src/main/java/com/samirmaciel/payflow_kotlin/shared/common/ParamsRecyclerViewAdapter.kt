package com.samirmaciel.payflow_kotlin.shared.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.shared.model.paymentslip.PaymentSlip
import com.samirmaciel.payflow_kotlin.shared.model.statiment.Statiment
import kotlinx.android.synthetic.main.paymentslip_item.view.*

class ParamsRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var statimentList : List<Statiment> = ArrayList()
    private var paymentslipList : List<PaymentSlip> = ArrayList()




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.paymentslip_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        if(paymentslipList.isEmpty()){
            return statimentList.size
        }

        return paymentslipList.size

    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.textName
        private val dueDate = itemView.textDueDate
        private val value = itemView.textValue

        fun bind(paymentslip : PaymentSlip?, statiment : Statiment?){

            if(paymentslip != null){

                name.text = paymentslip.name
                dueDate.text = paymentslip.dueDate
                value.text = paymentslip.value

            }else{

                name.text = statiment?.name
                dueDate.text = statiment?.dueDate
                value.text = statiment?.value
            }

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){

            is MyViewHolder -> {
                if(paymentslipList.isEmpty()){
                    holder.bind(statiment = statimentList.get(position), paymentslip = null)
                }else{
                    holder.bind(paymentslip = paymentslipList.get(position), statiment = null)
                }
            }
        }

    }

    fun setPaymentList(list : List<PaymentSlip>){
        this.paymentslipList = list
    }

    fun setStatimentList(list : List<Statiment>){
        this.statimentList = list
    }
}