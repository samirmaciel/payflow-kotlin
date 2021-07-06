package com.samirmaciel.payflow_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samirmaciel.payflow_kotlin.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onStart() {
        super.onStart()

        binding.btnEntrar.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}