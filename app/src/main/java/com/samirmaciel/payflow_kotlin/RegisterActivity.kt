package com.samirmaciel.payflow_kotlin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.samirmaciel.payflow_kotlin.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var inputName : TextInputEditText
    private lateinit var inputDuedate : TextInputEditText
    private lateinit var inputWallet : TextInputEditText
    private lateinit var inputBarcode : TextInputEditText
    private lateinit var buttonRegister : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inputName = binding.inputTextName.editText as TextInputEditText
        inputDuedate = binding.inputTextDueDate.editText as TextInputEditText
        inputWallet = binding.inputTextWallet.editText as TextInputEditText
        inputBarcode = binding.inputTextBarcode.editText as TextInputEditText
        buttonRegister = binding.buttonRegister as Button

    }

    private fun validateName() : Boolean {
        var nameText : String = inputName.text.toString().trim()

        if(nameText.isEmpty()){
            inputName.error = "De um nome para seu boleto"
            return false
        }
        return true
    }

    private fun validateDueDate() : Boolean {
        var nameText : String = inputDuedate.text.toString().trim()

        if(nameText.isEmpty()){
            inputDuedate.error = "Insira a data de vencimento"
            return false
        }
        return true
    }

    private fun validateWallet() : Boolean {
        var nameText : String = inputWallet.text.toString().trim()

        if(nameText.isEmpty()){
            inputWallet.error = "Insira o valor"
            return false
        }
        return true
    }

    private fun validateBarCode() : Boolean {
        var nameText : String = inputBarcode.text.toString().trim()

        if(nameText.isEmpty()){
            inputBarcode.error = "Insira o código de barras"
            return false
        }
        return true
    }


    override fun onStart() {
        super.onStart()

        buttonRegister.setOnClickListener(){
            validateName()
            validateDueDate()
            validateWallet()
            validateBarCode()
        }


    }
}