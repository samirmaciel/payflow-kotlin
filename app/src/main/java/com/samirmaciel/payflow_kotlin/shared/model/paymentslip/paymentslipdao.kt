package com.samirmaciel.payflow_kotlin.shared.model.paymentslip

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samirmaciel.payflow_kotlin.shared.model.PaymentSlip

@Dao
interface PaymentSlipeDAO{

    @Query("SELECT * FROM paymentslip")
    fun getAll(): List<PaymentSlip>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(payment: PaymentSlip)

    @Query("DELETE FROM paymentslip")
    suspend fun deleteAll()
}