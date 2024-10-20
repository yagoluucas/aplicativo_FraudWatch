package com.example.fraud_watch.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fraud_watch.R
import com.example.fraud_watch.fragments.BottomNavigationMenu

class HomeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}