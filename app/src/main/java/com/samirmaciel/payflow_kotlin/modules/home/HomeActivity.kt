package com.samirmaciel.payflow_kotlin.modules.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.modules.barcodescanner.BarcodeScannerActivity
import com.samirmaciel.payflow_kotlin.modules.mypayments.MyPaymentsSlipsFragment
import com.samirmaciel.payflow_kotlin.modules.mystatiments.MyStatimentsFragment
import com.samirmaciel.payflow_kotlin.modules.register.RegisterActivity
import com.samirmaciel.payflow_kotlin.shared.data.AppDataBase
import com.samirmaciel.payflow_kotlin.shared.data.PaymentSlipDataSource
import com.samirmaciel.payflow_kotlin.shared.model.datarepository.RegistrationViewParams
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(){

    private val viewModel : HomeViewModel by viewModels({
        HomeViewModel.HomeViewModelFactory(PaymentSlipDataSource(AppDataBase.getDatabase(this).PaymentSlipDao()))
    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.homeContainer, MyPaymentsSlipsFragment())
                .commitAllowingStateLoss()
        }

        buttonAdd.setOnClickListener{
            startActivity(Intent(this,  BarcodeScannerActivity::class.java))
        }

        buttonHome.setOnClickListener{
            if(viewModel.index != 0){
                supportFragmentManager.beginTransaction()
                        .replace(R.id.homeContainer, MyPaymentsSlipsFragment())
                        .commitAllowingStateLoss()
                buttonHome.setColorFilter(resources.getColor(R.color.primaryColor))
                buttonList.setColorFilter(resources.getColor(R.color.heading))
                viewModel.index = 0
            }
        }

        buttonList.setOnClickListener{
            if(viewModel.index != 1){
                supportFragmentManager.beginTransaction()
                        .replace(R.id.homeContainer, MyStatimentsFragment())
                        .commitAllowingStateLoss()
                buttonHome.setColorFilter(resources.getColor(R.color.heading))
                buttonList.setColorFilter(resources.getColor(R.color.primaryColor))
                viewModel.index = 1
            }
        }

    }

    override fun onStart() {
        super.onStart()

        val userName = intent.getStringExtra("name").toString()
        val photoUrl = intent.getStringExtra("photoUrl").toString()

        textUserName.text = userName

        Log.d("PHOTOLOAD", "onStart: " + photoUrl)

        Picasso.get().load("https://lh3.googleusercontent.com/a-/AOh14Gh3LniOV01bWE2FQO-lUp5jcvYrO2mG1twavd_bWb0").into(imageUserProfile)

        Log.d("PHOTO", "onStart: " + photoUrl)

        viewModel.findAllPaymentSlip()
        viewModel.paymentslipList.observe(this, {list ->
            countPayments(list.size)
        })

    }


    private fun countPayments(count : Int){
        textCardPayments.text = "Você possui $count boletos cadastrados para pagar"
    }


}