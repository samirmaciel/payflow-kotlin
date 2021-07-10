package com.samirmaciel.payflow_kotlin.shared.model.datarepository

data class RegistrationViewParams (
    var id : Long = 0,
    var name : String = "",
    var dueDate : String = "",
    var value : String = "",
    var barcode : String = ""
)
