package com.example.fraud_watch.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fraud_watch.R

class ProcedimentosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_procedimentos)
    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}