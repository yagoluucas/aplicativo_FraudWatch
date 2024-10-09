package com.example.fraud_watch.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.fraud_watch.R
import com.example.fraud_watch.fragments.BtnCloseCadastro
import com.example.fraud_watch.model.User
import com.example.fraud_watch.utils.Utils

class FinishActivityRegistration: AppCompatActivity() {

    val utils: Utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_registration)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BtnCloseCadastro())
                .commit()
        }

        val btnFinalizarCadastro: Button = findViewById(R.id.Finish_BtnFinalizarCadastro_Button)
        val user: User? = intent.getSerializableExtra("user") as? User
        val campoEmail: EditText = findViewById(R.id.Finish_CampoEmail_EditText)
        val campoTelefone: EditText = findViewById(R.id.Finish_CampoTelefone_EditText)
        val campoConfirmarSenha: EditText = findViewById(R.id.Finish_CampoComfirmarSenha_EditText)
        val campoSenha: EditText = findViewById(R.id.Finish_CampoSenha_EditText)

        btnFinalizarCadastro.setOnClickListener{
            validaCampos(campoEmail, campoTelefone, campoSenha, campoConfirmarSenha)

            user?.email = campoEmail.text.toString()
            user?.senha = campoConfirmarSenha.text.toString()
            user?.telefone = campoTelefone.text.toString()
            Log.v("usuário finalizado", user.toString())
        }
    }

    fun validaCampos(campoEmail: EditText,
                     campoTelefone: EditText,
                     campoSenha: EditText,
                     campoConfirmarSenha: EditText): Boolean{

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(campoEmail.text.toString()).matches()){
            campoEmail.error = "Email inválido!"
            return false
        }

        if(campoTelefone.length() != 11){
            campoTelefone.error = "telefone inválido"
            return false
        }

        if(campoSenha.length() < 8){
            campoSenha.error = "A senha deve conter no mínimo 8 caracteres"
            return false
        }

        Log.v("senha", campoSenha.text.toString())
        Log.v("senha 2", campoConfirmarSenha.text.toString())

        if(campoSenha.text.toString() != campoConfirmarSenha.text.toString()){
            campoConfirmarSenha.error = "Senhas não correspondem"
            return false
        }
        return true
    }
}