package com.samirmaciel.payflow_kotlin.shared.model.statiment

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Statiment(
    val id : Long = 0,
    val name : String,
    val dueDate : String,
    val value : String,
    val barcode : String
)
