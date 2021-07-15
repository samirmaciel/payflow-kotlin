package com.samirmaciel.payflow_kotlin.shared.common

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.google.android.material.textfield.TextInputEditText
import java.lang.NumberFormatException
import java.text.NumberFormat


class MoneyTextWatcher( val editText : TextInputEditText) : TextWatcher{


    override fun afterTextChanged(editable: Editable?) {


    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if(!s.toString().isEmpty()){
            editText.removeTextChangedListener(this)

            var cleanString = s.toString().replace("[R$,.]".toRegex(), "").replace("\\s+".toRegex(), "")

            if(cleanString.length != 0){
                try {
                    var parsed : Double
                    var formatted : String
                    parsed = cleanString.toDouble()
                    formatted = NumberFormat.getCurrencyInstance().format(parsed / 100)

                    editText.setText(formatted)
                    editText.setSelection(formatted.length)
                }catch (e : NumberFormatException){

                }
            }
            editText.addTextChangedListener(this)
        }
    }

}