package com.samirmaciel.payflow_kotlin.modules.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.google.android.material.textfield.TextInputEditText
import com.samirmaciel.payflow_kotlin.databinding.ActivityRegisterBinding
import com.samirmaciel.payflow_kotlin.modules.home.HomeActivity
import com.samirmaciel.payflow_kotlin.shared.common.DateTextWatcher
import com.samirmaciel.payflow_kotlin.shared.common.MoneyTextWatcher
import com.samirmaciel.payflow_kotlin.shared.data.AppDataBase
import com.samirmaciel.payflow_kotlin.shared.data.PaymentSlipDataSource
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.RegistrationViewParams

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var inputName : TextInputEditText
    private lateinit var inputDuedate : TextInputEditText
    private lateinit var inputWallet : TextInputEditText
    private lateinit var inputBarcode : TextInputEditText
    private lateinit var buttonRegister : Button

    private val viewModel : RegistrationViewModel by viewModels({
        RegistrationViewModel.RegistrationViewModelFactory(PaymentSlipDataSource(AppDataBase.getDatabase(this).PaymentSlipDao()))
    })



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inputName = binding.inputTextName.editText as TextInputEditText
        inputDuedate = binding.inputTextDueDate.editText as TextInputEditText
        inputWallet = binding.inputTextWallet.editText as TextInputEditText
        inputBarcode = binding.inputTextBarcode.editText as TextInputEditText
        buttonRegister = binding.buttonRegister
        inputWallet.addTextChangedListener(
            MoneyTextWatcher(
                inputWallet
            )
        )

        inputDuedate.addTextChangedListener(DateTextWatcher(
                inputDuedate
        ))






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
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }

        viewModel.paymentList.observe(this, { lives ->
            Log.d("OBSERVE", "onStart: " + lives.size)
        })


    }
}