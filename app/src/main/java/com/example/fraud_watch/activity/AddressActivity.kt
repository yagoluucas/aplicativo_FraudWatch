package com.example.fraud_watch.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fraud_watch.R
import com.example.fraud_watch.fragments.BtnCloseCadastro

class AddressActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        if (savedInstanceState == null) {
            // Adiciona o fragmento ao container (frame layout)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BtnCloseCadastro())
                .commit()
        }
    }
}