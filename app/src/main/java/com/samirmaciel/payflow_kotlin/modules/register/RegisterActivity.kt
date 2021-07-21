package com.samirmaciel.payflow_kotlin.modules.register

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
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
import androidx.annotation.RequiresApi
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
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun validateDueDate() : Boolean {
        var dateText : String = inputDueDate.text.toString().trim()
        var mounthString = "" + dateText[3] + dateText[4]
        var dayString = "" + dateText[0] + dateText[1]

        val mounth : Int
        val day : Int

        try{
            mounth = mounthString.toInt()
            day = dayString.toInt()
        }catch (e : Exception){
            return false
        }

        var dayCheck = 31
        when(mounth){
            1 -> { dayCheck = 31}
            2 -> { dayCheck = 28 }
            3 -> { dayCheck = 31 }
            4 -> { dayCheck = 30}
            5 -> { dayCheck = 31 }
            6 -> { dayCheck = 30 }
            7 -> { dayCheck = 31}
            8 -> { dayCheck = 31 }
            9 -> { dayCheck = 30 }
            10 -> { dayCheck = 31}
            11 -> { dayCheck = 30 }
            12 -> { dayCheck = 31 }
        }
        if(dateText.isEmpty() || mounth > 13 || day > dayCheck){
            inputDueDate.error = "Insira a data válida"
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


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        buttonRegister.setOnClickListener(){
            if(validateName() && validateDueDate()
                    && validateWallet()
                    && validateBarCode()){
                viewModel.savePaymentSlip(getPaymentFromUI())
                val toast = Toast(this)
                toast.apply {
                    setGravity(Gravity.TOP, 0 , 0)
                    duration = Toast.LENGTH_SHORT
                    view = layoutInflater.inflate(R.layout.custom_toast_sucessful, findViewById<ViewGroup>(R.id.toast_layout), false)
                }
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