package com.samirmaciel.payflow_kotlin.shared.model.paymentslip


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PaymentSlipDAO{

    @Query("SELECT * FROM PaymentSlipEntity")
    suspend fun findAll(): MutableList<PaymentSlipEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(paymentEntity: PaymentSlipEntity)

    @Query("DELETE FROM paymentslipentity")
    suspend fun deleteAll()

    @Query("SELECT * FROM paymentslipentity WHERE id = :id")
    suspend fun findById(id : Long) : PaymentSlipEntity

    @Query("DELETE FROM paymentslipentity WHERE id = :id")
    suspend fun deleteById(id : Long)

}