package com.samirmaciel.payflow_kotlin.modules.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.databinding.ActivityLoginBinding
import com.samirmaciel.payflow_kotlin.modules.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)


    }

    override fun onStart() {
        super.onStart()

        btnEntrar.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}