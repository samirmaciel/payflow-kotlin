package com.samirmaciel.payflow_kotlin.modules.home

import android.content.Context
import android.content.Intent
import android.media.MediaCodec.MetricsConstants.MODE
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.transition.Visibility
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.observe
import com.google.android.gms.auth.api.signin.GoogleSignIn
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
import kotlinx.android.synthetic.main.activity_register.*


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
//                TransitionManager.beginDelayedTransition(constraint_home, AutoTransition())
//                countPaymentsCardView.visibility = View.VISIBLE
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
//                TransitionManager.beginDelayedTransition(constraint_home, AutoTransition())
//                countPaymentsCardView.visibility = View.GONE
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
        val account = GoogleSignIn.getLastSignedInAccount(this)

        textUserName.text = account?.displayName.toString()
        Picasso.get().load(account?.photoUrl.toString()).into(imageUserProfile)

        viewModel.findAllPaymentSlip()

        viewModel.paymentslipList.observe(this, {list ->
            countPayments(list.size)
        })
    }


    private fun countPayments(count : Int){
        var boleto = if(count <= 1) "boleto" else "boletos"
        var cadastrado = if(count <= 1) "cadastrado" else "cadastrados"
        var num = if(count == 0) "nenhum" else "$count"
        var text = "Você possui <b>$num $boleto</b> $cadastrado para pagar"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textCardPayments.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
        }
    }


}