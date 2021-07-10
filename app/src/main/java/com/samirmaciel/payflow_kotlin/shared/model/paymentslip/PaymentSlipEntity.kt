package com.samirmaciel.payflow_kotlin.shared.model.paymentslip

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.RegistrationViewParams

@Entity
data class PaymentSlipEntity(

    @PrimaryKey(autoGenerate = true) val id : Long = 0,
    @ColumnInfo val name : String,
    @ColumnInfo val dueDate : String,
    @ColumnInfo val value : String,
    @ColumnInfo val barcode : String
)

fun RegistrationViewParams.toPaymentSlipEntity() : PaymentSlipEntity {
    return PaymentSlipEntity(
        id = this.id,
        name = this.name,
        dueDate = this.dueDate,
        value = this.value,
        barcode = this.barcode
    )

}

fun PaymentSlipEntity.toPaymentSlip() : PaymentSlip{
    return PaymentSlip(
        id = this.id,
        name = this.name,
        dueDate = this.dueDate,
        value = this.value,
        barcode = this.barcode
    )

}