package com.samirmaciel.payflow_kotlin.shared.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.samirmaciel.payflow_kotlin.shared.model.paymentslip.PaymentSlipDAO
import com.samirmaciel.payflow_kotlin.shared.model.paymentslip.PaymentSlipEntity
import com.samirmaciel.payflow_kotlin.shared.model.statiment.StatimentDAO
import com.samirmaciel.payflow_kotlin.shared.model.statiment.StatimentEntity

@Database (entities = [PaymentSlipEntity::class, StatimentEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun PaymentSlipDao() : PaymentSlipDAO
    abstract fun StatimentDao() : StatimentDAO

    companion object {

        @Volatile
        private var INSTANCE : AppDataBase? = null

        fun getDatabase(context : Context): AppDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}