package com.samirmaciel.payflow_kotlin.modules.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.samirmaciel.payflow_kotlin.shared.common.CurrencyTextWatcher
import com.samirmaciel.payflow_kotlin.databinding.ActivityRegisterBinding
import com.samirmaciel.payflow_kotlin.shared.data.AppDataBase
import com.samirmaciel.payflow_kotlin.shared.model.PaymentSlipDataSource
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.PaymentSlipRepository
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.RegistrationViewParams

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var inputName : TextInputEditText
    private lateinit var inputDuedate : TextInputEditText
    private lateinit var inputWallet : TextInputEditText
    private lateinit var inputBarcode : TextInputEditText
    private lateinit var buttonRegister : Button

    private lateinit var viewModel : RegistrationViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inputName = binding.inputTextName.editText as TextInputEditText
        inputDuedate = binding.inputTextDueDate.editText as TextInputEditText
        inputWallet = binding.inputTextWallet.editText as TextInputEditText
        inputBarcode = binding.inputTextBarcode.editText as TextInputEditText
        buttonRegister = binding.buttonRegister as Button
        inputWallet.addTextChangedListener(
            CurrencyTextWatcher(
                inputWallet
            )
        )

        val database = AppDataBase.getDatabase(this)
        viewModel = ViewModelProvider(this, RegistrationViewModel.RegistrationViewModelFactory(PaymentSlipDataSource(database.PaymentSlipDao()))).get(RegistrationViewModel::class.java)




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
            if(validateName() && validateDueDate()
                && validateWallet()
                && validateBarCode()){
                val registrationViewParams : RegistrationViewParams = RegistrationViewParams(name = inputName.text.toString(),
                    dueDate = inputDuedate.text.toString(),
                    value = inputWallet.text.toString(),
                    barcode = inputBarcode.text.toString())

                viewModel.savePaymentSlip(registrationViewParams)

            }
        }


    }
}