package com.samirmaciel.payflow_kotlin.modules.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.databinding.ActivityLoginBinding
import com.samirmaciel.payflow_kotlin.modules.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 100
    private val viewModel : LoginViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


    }

    override fun onStart() {
        super.onStart()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        btnEntrar.setOnClickListener {
            signIn()
        }
    }

    private fun signIn(){
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("name", account.displayName)
                intent.putExtra("photoUrl", account.photoUrl)
                startActivity(intent)

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("ERROU", "Google sign in failed" + e.message.toString())
            }
        }
    }
}