package com.samirmaciel.payflow_kotlin.shared.common

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat


class MoneyTextWatcher( val editText : TextInputEditText) : TextWatcher{




    private var current = ""




    override fun afterTextChanged(editable: Editable?) {


    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        var cleanString : String = s.toString().replace("R$" , "").replace(".", "").replace(",","").trim()

        var parsed : Double = cleanString.toDouble()
        var input : Double = parsed / 100



        Log.d("TESTE",  NumberFormat.getCurrencyInstance().format(input))

        this.current = NumberFormat.getCurrencyInstance().format(input)


    }

}