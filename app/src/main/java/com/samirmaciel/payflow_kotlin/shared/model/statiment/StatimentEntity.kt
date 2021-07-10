package com.samirmaciel.payflow_kotlin.shared.model.statiment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.RegistrationViewParams

@Entity
data class StatimentEntity(
    @PrimaryKey(autoGenerate = true) val id : Long = 0,
    @ColumnInfo val name : String,
    @ColumnInfo val dueDate : String,
    @ColumnInfo val value : String,
    @ColumnInfo val barcode : String
)

fun RegistrationViewParams.toStatimentEntity() : StatimentEntity {
    return StatimentEntity(
        id = this.id,
        name = this.name,
        dueDate = this.dueDate,
        value = this.value,
        barcode = this.barcode
    )
}

fun StatimentEntity.toStatiment() : Statiment {
    return Statiment(
        id = this.id,
        name = this.name,
        dueDate = this.dueDate,
        value = this.value,
        barcode = this.barcode
    )
}

