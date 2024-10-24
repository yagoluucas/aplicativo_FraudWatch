package com.example.fraud_watch.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fraud_watch.R

class DicasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dicas)

        val primeiroTituloEvitarFraudes: TextView = findViewById(R.id.Dicas_TituloDicasEvitarFraude1_TextView)
        val primeiraDescricaoEvitarFraudes: TextView = findViewById(R.id.Dicas_DescricaoDicasEvitarFraude1_TextView)

        primeiroTituloEvitarFraudes.setOnClickListener {
            mostrarDescricaoDoTitulo(primeiraDescricaoEvitarFraudes)
        }
    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun mostrarDescricaoDoTitulo(campoDescricao: TextView){
        if (campoDescricao.visibility == View.GONE) campoDescricao.visibility = View.VISIBLE
        else campoDescricao.visibility = View.GONE
    }
}