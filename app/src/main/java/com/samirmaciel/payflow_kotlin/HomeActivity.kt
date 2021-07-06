package com.samirmaciel.payflow_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samirmaciel.payflow_kotlin.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var count : Int = 14

        binding.textCardCadastrados.text = "Você possui $count boletos cadastrados para pagar"
    }
}