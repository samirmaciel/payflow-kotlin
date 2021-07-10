package com.samirmaciel.payflow_kotlin.shared.model.paymentslip


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PaymentSlipDAO{

    @Query("SELECT * FROM paymentslipentity")
    fun findAll(): List<PaymentSlipEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(paymentEntity: PaymentSlipEntity)

    @Query("DELETE FROM paymentslipentity")
    suspend fun deleteAll()

    @Query("SELECT * FROM paymentslipentity WHERE id = :id")
    fun findById(id : Long) : PaymentSlipEntity

    @Query("DELETE FROM paymentslipentity WHERE id = :id")
    fun deleteById(id : Long)

}