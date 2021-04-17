package com.example.android.appcamera

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import android.Manifest
import android.app.Activity
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {

    private val CAMERA_REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btPermission = findViewById<Button>(R.id.btPermission)

        btPermission.setOnClickListener {
            checkCameraPermission()
        }
    }

    private fun checkCameraPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED){
            requestCameraPermission()
        }
        else{
            Toast.makeText(this, "Ya se cuanta con permiso a la camara",
            Toast.LENGTH_LONG).show()
        }
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
            Toast.makeText(this,"Antes rechazo el permiso, Habilitelo manualmente ctmr",
                Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Debe aceptar el permiso mi pana",
                Toast.LENGTH_LONG).show()
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        }
    }

    //onRequestPermissionResult (ctrl + O)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            CAMERA_REQUEST_CODE -> {
                if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED))
                {
                    Toast.makeText(this, "Se autorizo el permiso de la camara",
                        Toast.LENGTH_LONG).show()

                    // Aca la funcionalidad cuando tenga permiso
                }
                else{
                    Toast.makeText(this, "Permiso denegado",
                        Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }
}