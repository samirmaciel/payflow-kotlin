package com.samirmaciel.payflow_kotlin.shared.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Statiment(
    @PrimaryKey(autoGenerate = true) var id : Int,
    @ColumnInfo var name : String,
    @ColumnInfo var dueDate : String,
    @ColumnInfo var value : String,
    @ColumnInfo var barcode : String
)
