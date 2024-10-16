package com.example.fraud_watch.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.fraud_watch.R
import com.example.fraud_watch.fragments.ArrowReturnCadastro
import com.example.fraud_watch.fragments.BtnCloseCadastro
import com.example.fraud_watch.model.User
import com.example.fraud_watch.utils.Utils

class FinishActivityRegistration: AppCompatActivity() {

    val utils: Utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_registration)

        val btnFinalizarCadastro: Button = findViewById(R.id.Finish_BtnFinalizarCadastro_Button)
        val user: User? = intent.getSerializableExtra("user") as? User
        val campoEmail: EditText = findViewById(R.id.Finish_CampoEmail_EditText)
        val campoTelefone: EditText = findViewById(R.id.Finish_CampoTelefone_EditText)
        val campoConfirmarSenha: EditText = findViewById(R.id.Finish_CampoComfirmarSenha_EditText)
        val campoSenha: EditText = findViewById(R.id.Finish_CampoSenha_EditText)

        val fragment = ArrowReturnCadastro.newInstance("finish_activity")
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container2, fragment)
            .commit()

        formatarCampoTelefone(campoTelefone)
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

        if(campoTelefone.length() != 13){
            campoTelefone.error = "telefone inválido"
            return false
        }

        if(campoSenha.length() < 8){
            campoSenha.error = "A senha deve conter no mínimo 8 caracteres"
            return false
        }

        if(campoSenha.text.toString() != campoConfirmarSenha.text.toString()){
            campoConfirmarSenha.error = "Senhas não correspondem"
            return false
        }
        return true
    }


    fun formatarCampoTelefone(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false
            private val mascara = "## #####-####"
            private var oldText = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                if (isUpdating) {
                    return
                }

                // Remove qualquer caractere que não seja número
                val text = editable.toString().replace(Regex("[^\\d]"), "")
                var formattedText = ""

                var i = 0
                for (m in mascara.toCharArray()) {
                    if (m != '#' && i < text.length) {
                        formattedText += m
                    } else {
                        try {
                            formattedText += text[i]
                        } catch (e: Exception) {
                            break
                        }
                        i++
                    }
                }

                isUpdating = true
                oldText = formattedText
                editText.setText(formattedText)
                editText.setSelection(formattedText.length) // Move o cursor para o final
                isUpdating = false
            }
        })
    }
}