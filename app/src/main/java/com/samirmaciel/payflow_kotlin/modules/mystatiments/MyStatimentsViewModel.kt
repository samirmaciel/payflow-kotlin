package com.samirmaciel.payflow_kotlin.modules.mystatiments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.StatimentRepository
import com.samirmaciel.payflow_kotlin.shared.model.statiment.Statiment
import kotlinx.coroutines.launch

class MyStatimentsViewModel(private val repository: StatimentRepository) : ViewModel() {

    var statimentList : MutableLiveData<MutableList<Statiment>> = MutableLiveData()

    init {
        updateStatimentList()
    }

    fun updateStatimentList(){
        viewModelScope.launch {
            statimentList.postValue(repository.findAll())
        }
    }

    fun deleteById(id : Long){

        viewModelScope.launch {
            repository.deleteById(id)
            statimentList.postValue(repository.findAll())
        }
    }

    class StatimentViewModelFactory(private val repository: StatimentRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MyStatimentsViewModel(repository) as T
        }
    }
}