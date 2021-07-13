package com.samirmaciel.payflow_kotlin.shared.data

import com.samirmaciel.payflow_kotlin.shared.model.datarepository.RegistrationViewParams
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.StatimentRepository
import com.samirmaciel.payflow_kotlin.shared.model.statiment.*

class StatimentDataSource( private val statimentDao : StatimentDAO) : StatimentRepository{

    override suspend fun save(registrationViewParms: RegistrationViewParams) {
        val statimentEntity : StatimentEntity = registrationViewParms.toStatimentEntity()
        statimentDao.save(statimentEntity)
    }

    override suspend fun findById(id: Long): Statiment {
        return statimentDao.findById(id).toStatiment()
    }

    override suspend fun findAll(): List<Statiment> {
        val statimentEntityList = statimentDao.findAll()
        var statimentList : List<Statiment> = listOf()
        for (statiment in statimentEntityList){
            statimentList.toMutableList().add(statiment.toStatiment())
        }
        return statimentList
    }

    override suspend fun deleteById(id: Long) {
        statimentDao.deleteById(id)
    }
}