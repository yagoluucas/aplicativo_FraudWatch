package com.example.fraud_watch.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.fraud_watch.R
import com.example.fraud_watch.fragments.BtnCloseCadastro

class PersonalInfoActivity: AppCompatActivity() {
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

        btnInformarEndereco.setOnClickListener(){
            if(validaCampos(campoCpf, campoNome, campoSobrenome)){
                val intent = Intent(this, AddressActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun validaCpf(cpf: String): Boolean{
        val regex = "[0-9]{11}".toRegex()
        return regex.matches(cpf)
    }

    fun validaCampoNome(texto: String): Boolean{
        val regex = "^[A-ZÀ-Ÿa-zà-ÿ]+(?:\\s[A-ZÀ-Ÿa-zà-ÿ]+)*\$".toRegex()
        return regex.matches(texto)
    }

    fun validaCampos(campoCpf: EditText, campoNome: EditText, campoSobrenome: EditText):Boolean{
        if(!validaCampoNome(campoNome.text.toString())) {
            campoNome.error = "Nome inválido"
            return false
        }

        if(!validaCampoNome(campoSobrenome.text.toString())) {
            campoSobrenome.error = "Sobrenome inválido"
            return false
        }

        if(!validaCpf(campoCpf.text.toString()) || campoCpf.text.toString() == ""){
            campoCpf.error = "cpf inválido, coloque somente números sem pontos ou traços"
            return false
        }
        return true
    }
}
