package com.samirmaciel.payflow_kotlin.modules.barcodescanner


import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.samirmaciel.payflow_kotlin.R
import com.samirmaciel.payflow_kotlin.modules.home.HomeActivity
import com.samirmaciel.payflow_kotlin.modules.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_barcode_scanner.*



class BarcodeScannerActivity : AppCompatActivity() {

    private val CAMERA_REQUEST_CODE = 94
    private lateinit var codeScanner : CodeScanner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_scanner)

        startCodeScanner()
    }

    override fun onStart() {
        super.onStart()
        checkPermissions()
    }


    private fun startCodeScanner(){
        codeScanner = CodeScanner(this, scannerContainer)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            isAutoFocusEnabled = true
            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
        }
        codeScanner.decodeCallback = DecodeCallback {
            gotToRegisterActivity(it.text)
        }
        codeScanner.errorCallback  = ErrorCallback {
            gotToHome()
        }
    }

    private fun checkPermissions(){
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        if(permission != PackageManager.PERMISSION_GRANTED){
            makeRequestPermissions()
        }else{
            codeScanner.startPreview()
        }
    }

    private fun makeRequestPermissions(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
    }

    fun gotToHome(){
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun gotToRegisterActivity(result : String){
        val intent = Intent(this, RegisterActivity::class.java)
        intent.putExtra("result" , result)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

        startActivity(intent)
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){

            CAMERA_REQUEST_CODE -> {
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Você precisa aceitar as permissões para utilizar este app", Toast.LENGTH_SHORT).show()
                }else{

                    codeScanner.startPreview()

                }
            }
        }
    }


}


