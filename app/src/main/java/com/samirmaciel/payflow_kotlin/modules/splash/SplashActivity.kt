package com.samirmaciel.payflow_kotlin.modules.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.modules.home.HomeActivity
import com.samirmaciel.payflow_kotlin.modules.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({

            val account = GoogleSignIn.getLastSignedInAccount(this)


            if(account != null){
                startActivity(Intent(this, HomeActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }

        }, 1000)

    }
}