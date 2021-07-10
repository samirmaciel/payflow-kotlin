package com.samirmaciel.payflow_kotlin.shared.model.statiment

import androidx.room.*

@Dao
interface StatimentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(statiment : StatimentEntity)

    @Query("SELECT * FROM statimententity")
    fun findAll() : List<StatimentEntity>

    @Query("SELECT * FROM statimententity WHERE id = :id")
    fun findById(id : Long) : StatimentEntity

    @Query ("DELETE FROM statimententity WHERE id = :id")
    fun deleteById(id : Long)
}