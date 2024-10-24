package com.example.fraud_watch.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fraud_watch.R

class DicasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        setContentView(R.layout.activity_dicas)

        val tituloIds = arrayOf(
            R.id.Dicas_TituloDicasEvitarFraude1_TextView,
            R.id.Dicas_TituloDicasEvitarFraude2_TextView,
            R.id.Dicas_TituloDicasEvitarFraude3_TextView,
            R.id.Dicas_TituloDicasEvitarFraude4_TextView,
            R.id.Dicas_TituloDicasEvitarFraude5_TextView,
            R.id.Dicas_TituloDicasEvitarFraude6_TextView
        )

        val descricaoIds = arrayOf(
            R.id.Dicas_DescricaoDicasEvitarFraude1_TextView,
            R.id.Dicas_DescricaoDicasEvitarFraude2_TextView,
            R.id.Dicas_DescricaoDicasEvitarFraude3_TextView,
            R.id.Dicas_DescricaoDicasEvitarFraude4_TextView,
            R.id.Dicas_DescricaoDicasEvitarFraude5_TextView,
            R.id.Dicas_DescricaoDicasEvitarFraude6_TextView
        )

        for (i in tituloIds.indices) {
            val titulo = findViewById<TextView>(tituloIds[i])
            val descricao = findViewById<TextView>(descricaoIds[i])
            titulo.setOnClickListener {
                descricao.visibility = if (descricao.visibility == View.GONE) View.VISIBLE else View.GONE
            }
        }
    }
}
