package com.samirmaciel.payflow_kotlin.shared.model.statiment

import androidx.room.*

@Dao
interface StatimentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(statiment : StatimentEntity)

    @Query("SELECT * FROM statimententity")
    suspend fun findAll() : MutableList<StatimentEntity>

    @Query("SELECT * FROM statimententity WHERE id = :id")
    suspend fun findById(id : Long) : StatimentEntity

    @Query ("DELETE FROM statimententity WHERE id = :id")
    suspend fun deleteById(id : Long)
}