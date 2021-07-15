package com.samirmaciel.payflow_kotlin.modules.home

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.databinding.ActivityHomeBinding
import com.samirmaciel.payflow_kotlin.modules.mypayments.MyPaymentsSlipsFragment
import com.samirmaciel.payflow_kotlin.modules.mystatiments.MyStatimentsFragment
import com.samirmaciel.payflow_kotlin.modules.register.RegisterActivity
import com.samirmaciel.payflow_kotlin.shared.data.AppDataBase
import com.samirmaciel.payflow_kotlin.shared.data.PaymentSlipDataSource
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(){

    private val viewModel : HomeViewModel by viewModels({
        HomeViewModel.HomeViewModelFactory(PaymentSlipDataSource(AppDataBase.getDatabase(this).PaymentSlipDao()))
    })

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.homeContainer, MyPaymentsSlipsFragment())
                .commitAllowingStateLoss()
        }

        buttonAdd.setOnClickListener{
            startActivity(Intent(this,  RegisterActivity::class.java))
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

        viewModel.findAllPaymentSlip()
        viewModel.paymentslipList.observe(this, {list ->
            countPayments(list.size)
        })

    }


    private fun countPayments(count : Int){
        textCardPayments.text = "Você possui $count boletos cadastrados para pagar"
    }


}