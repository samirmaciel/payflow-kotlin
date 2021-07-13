package com.samirmaciel.payflow_kotlin.shared.model.paymentslip

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.RegistrationViewParams
import com.samirmaciel.payflow_kotlin.shared.model.statiment.Statiment

data class PaymentSlip (
        val id : Long = 0,
        val name : String,
        val dueDate : String,
        val value : String,
        val barcode : String
)

fun PaymentSlip.toStatiment() : Statiment {
    return Statiment(
            name = this.name,
            dueDate = this.dueDate,
            value = this.value,
            barcode = this.barcode

    )
}

fun PaymentSlip.toRegistrationViewParms() : RegistrationViewParams{
    return RegistrationViewParams(
            name = this.name,
            dueDate = this.dueDate,
            value = this.value,
            barcode = this.barcode
    )
}

