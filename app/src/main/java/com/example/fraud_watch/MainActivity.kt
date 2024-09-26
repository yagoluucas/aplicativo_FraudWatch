package com.example.fraud_watch


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fraud_watch.activity.PersonalInfoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val campoEmail = findViewById<EditText>(R.id.LOGIN_CampoEmail_editTextTextEmailAddress)
        val campoSenha = findViewById<EditText>(R.id.LOGIN_CampoSenha_editTextText)
        val btnEntrar = findViewById<Button>(R.id.LOGIN_BtnEntrar_Button)

        val btnCriarConta = findViewById<Button>(R.id.LOGIN_BtnCriarConta_Button)
        btnCriarConta.setOnClickListener(){
            val intent = Intent(this, PersonalInfoActivity::class.java)
            startActivity(intent)
        }

        btnEntrar.setOnClickListener(){
            if(campoEmail.text.toString().equals(""))campoEmail.error = "informe a sua senha"

            if(campoSenha.text.toString().equals(""))campoSenha.error = "informe o seu email"
        }
    }
}