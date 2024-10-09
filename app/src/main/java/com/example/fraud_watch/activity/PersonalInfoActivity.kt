package com.example.fraud_watch.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.fraud_watch.R
import com.example.fraud_watch.fragments.BtnCloseCadastro
import com.example.fraud_watch.model.User
import com.example.fraud_watch.utils.Utils
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class PersonalInfoActivity: AppCompatActivity() {

    private final val utils : Utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info)

        if (savedInstanceState == null) {
            // Adiciona o fragmento ao container (frame layout)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BtnCloseCadastro())
                .commit()
        }

        val btnInformarEndereco = findViewById<Button>(R.id.PersonalInfo_BtnAvancar_Button)
        val campoNome = findViewById<EditText>(R.id.PersonalInfo_CampoNome_EditText)
        val campoSobrenome = findViewById<EditText>(R.id.PersonalInfo_CampoSobrenome_EditText)
        val campoCpf = findViewById<EditText>(R.id.PersonalInfo_CampoCpf_EditText)
        val campoDataNascimento = findViewById<EditText>(R.id.PersonalInfo_CampoDataNascimento_EditText)

        btnInformarEndereco.setOnClickListener{
            if(validaCampos(campoCpf, campoNome, campoSobrenome, campoDataNascimento)){
                val user: User = User(campoNome.text.toString(),
                    campoSobrenome.text.toString(),
                    campoCpf.text.toString(),
                    LocalDate.parse(campoDataNascimento.text.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                )

                val intent = Intent(this, AddressActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }
        }

        formatarCampoCPF(campoCpf)
        formatarCampoData(campoDataNascimento)
    }

    fun validaCampos(campoCpf: EditText, campoNome: EditText, campoSobrenome: EditText, campoData: EditText):Boolean{
        if(!utils.validaCampoNome(campoNome.text.toString())) {
            campoNome.error = "Nome inválido"
            return false
        }

        if(!utils.validaCampoNome(campoSobrenome.text.toString())) {
            campoSobrenome.error = "Sobrenome inválido"
            return false
        }

        if(!utils.validaCpf(campoCpf.text.toString())){
            campoCpf.error = "cpf inválido, coloque somente números sem pontos ou traços"
            return false
        }

        if(campoData.text.toString().length <= 9){
            campoData.error = "Data de nascimento incorreta!"
            return false
        }
        return true
    }

    fun formatarCampoData(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false
            private val mascara = "##/##/####"
            private var oldText = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                if (isUpdating) {
                    return
                }

                val text = editable.toString().replace(Regex("[^\\d]"), "") // Remove qualquer caractere não numérico
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

    fun formatarCampoCPF(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false
            private val mascara = "###.###.###-##"
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
