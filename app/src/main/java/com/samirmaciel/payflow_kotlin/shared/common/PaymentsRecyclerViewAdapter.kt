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

class PaymentsRecyclerViewAdapter(private val onItemClicked : (PaymentSlip) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var itemList : List<PaymentSlip> = ArrayList()




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.paymentslip_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return itemList.size

    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.textName
        private val dueDate = itemView.textDueDate
        private val value = itemView.textValue

        fun bind(onItemClicked: (PaymentSlip) -> kotlin.Unit, paymentslip : PaymentSlip){


            name.text = paymentslip.name
            dueDate.text = paymentslip.dueDate
            value.text = paymentslip.value


            itemView.setOnClickListener{

                onItemClicked(paymentslip)

            }

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){

            is MyViewHolder -> {

                holder.bind(onItemClicked, itemList[position])
            }
        }

    }

    fun setItemList(list : List<PaymentSlip>){
        this.itemList = list
    }

}