package com.samirmaciel.payflow_kotlin.shared.model.paymentslip

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class PaymentSlip (
        val id : Long = 0,
        val name : String,
        val dueDate : String,
        val value : String,
        val barcode : String
)

