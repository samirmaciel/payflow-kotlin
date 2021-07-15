package com.samirmaciel.payflow_kotlin.shared.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.shared.model.statiment.Statiment
import kotlinx.android.synthetic.main.paymentslip_item.view.*

class StatimentsRecyclerViewAdapter(private val onItemClicked : (Statiment) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemlist : MutableList<Statiment> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.paymentslip_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MyViewHolder -> {
                holder.bind(onItemClicked, itemlist[position])
            }
        }
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.textName
        private val dueDate = itemView.textDueDate
        private val value = itemView.textValue

        @SuppressLint("ResourceAsColor")
        fun bind(onItemClicked: (Statiment) -> kotlin.Unit, statiment : Statiment){
            name.setTextColor(R.color.delete)
            name.text = statiment.name
            dueDate.text = statiment.dueDate
            value.text = statiment.value

            itemView.setOnClickListener{
                onItemClicked(statiment)
            }
        }
    }

    fun setItemList(list : MutableList<Statiment>){
        itemlist = list
    }
}