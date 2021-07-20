package com.samirmaciel.payflow_kotlin.modules.register

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.modules.home.HomeActivity
import com.samirmaciel.payflow_kotlin.shared.common.DateTextWatcher
import com.samirmaciel.payflow_kotlin.shared.common.MoneyTextWatcher
import com.samirmaciel.payflow_kotlin.shared.data.AppDataBase
import com.samirmaciel.payflow_kotlin.shared.data.PaymentSlipDataSource
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.RegistrationViewParams
import com.samirmaciel.payflow_kotlin.shared.model.paymentslip.PaymentSlip
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
            if(intent.getStringExtra("result") != null) {
                setText(intent.getStringExtra("result"))
                isEnabled = false
                setTextColor(resources.getColor(R.color.verde))
            }
        }

        buttonCancel.setOnClickListener{
            goToHomePage()
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
                viewModel.savePaymentSlip(getPaymentFromUI())
                val toast = Toast.makeText(this, "Boleto cadastrado com sucesso!", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0 , 0)
                toast.view = layoutInflater.inflate(R.layout.custom_toast_sucessful, findViewById<ViewGroup>(R.id.toast_layout), false)
                toast.show()
                goToHomePage()
            }
        }
    }

    private fun getPaymentFromUI() : RegistrationViewParams {
            return RegistrationViewParams(name = inputName.text.toString(),
                    dueDate = inputDueDate.text.toString(),
                    value = inputWallet.text.toString(),
                    barcode = inputBarcode.text.toString())
    }

    private fun goToHomePage(){
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        goToHomePage()
    }
}