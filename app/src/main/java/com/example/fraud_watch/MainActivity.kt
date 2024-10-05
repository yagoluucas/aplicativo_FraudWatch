package com.example.fraud_watch


import android.app.AlertDialog
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
            val emailEstaVazio: Boolean = campoEmail.text.toString() == ""
            val senhaEstaVazia: Boolean = campoSenha.text.toString() == ""
            if(emailEstaVazio) campoEmail.error = "informe o seu email"
            if(senhaEstaVazia) campoSenha.error = "informe a sua senha"
            if(!emailEstaVazio && !senhaEstaVazia) criarAlertDialog()
        }
    }

    fun criarAlertDialog(){
        val alertDialog: AlertDialog = AlertDialog.Builder(this)
            .setTitle("Bem vindo Yago")
            .setMessage("Login realizado com sucesso")
            .setIcon(getDrawable(R.drawable.check))
            .setCancelable(false)
            .show()

        // time para apagar o alert
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            if(alertDialog.isShowing){
                alertDialog.dismiss()
            }
        }
    }
}