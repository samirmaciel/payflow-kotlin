package com.samirmaciel.payflow_kotlin.modules.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.databinding.ActivityHomeBinding
import com.samirmaciel.payflow_kotlin.databinding.PaymentslipItemBinding
import com.samirmaciel.payflow_kotlin.modules.mypayments.MyPaymentsSlipsFragment
import com.samirmaciel.payflow_kotlin.modules.register.RegisterActivity
import com.samirmaciel.payflow_kotlin.shared.common.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(){

    private var index : Int = 0

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
            //startActivity(Intent(this,  RegisterActivity::class.java))

        }

    }


}