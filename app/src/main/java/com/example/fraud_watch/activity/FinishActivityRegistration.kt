package com.example.fraud_watch.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.fraud_watch.R
import com.example.fraud_watch.fragments.BtnCloseCadastro

class FinishActivityRegistration: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_registration)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BtnCloseCadastro())
                .commit()
        }

        val btnFinalizarCadastro: Button = findViewById(R.id.Finish_BtnFinalizarCadastro_Button)

        btnFinalizarCadastro.setOnClickListener{

        }
    }
}