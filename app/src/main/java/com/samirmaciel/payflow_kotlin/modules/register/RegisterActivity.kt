package com.samirmaciel.payflow_kotlin.modules.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.databinding.ActivityRegisterBinding
import com.samirmaciel.payflow_kotlin.modules.home.HomeActivity
import com.samirmaciel.payflow_kotlin.shared.common.DateTextWatcher
import com.samirmaciel.payflow_kotlin.shared.common.MoneyTextWatcher
import com.samirmaciel.payflow_kotlin.shared.data.AppDataBase
import com.samirmaciel.payflow_kotlin.shared.data.PaymentSlipDataSource
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.RegistrationViewParams
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {



    private val viewModel : RegistrationViewModel by viewModels({
        RegistrationViewModel.RegistrationViewModelFactory(PaymentSlipDataSource(AppDataBase.getDatabase(this).PaymentSlipDao()))
    })



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        inputWallet.addTextChangedListener(
            MoneyTextWatcher(
                inputWallet
            )
        )

        inputDueDate.addTextChangedListener(DateTextWatcher(
                inputDueDate
        ))

        inputBarcode.apply {
            setText(intent.getStringExtra("result"))
            isEnabled = false
            setTextColor(resources.getColor(R.color.verde))
        }



        buttonCancel.setOnClickListener{

            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }




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
        var nameText : String = inputDueDate.text.toString().trim()

        if(nameText.isEmpty()){
            inputDueDate.error = "Insira a data de vencimento"
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
                    dueDate = inputDueDate.text.toString(),
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