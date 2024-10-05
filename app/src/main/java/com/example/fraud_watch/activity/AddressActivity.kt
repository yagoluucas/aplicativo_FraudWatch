package com.example.fraud_watch.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fraud_watch.R
import com.example.fraud_watch.fragments.BtnCloseCadastro

class AddressActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        if (savedInstanceState == null) {
            // Adiciona o fragmento ao container (frame layout)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BtnCloseCadastro())
                .commit()
        }

        // cria a lista
        val estado = listOf(
            "Acre",
            "Alagoas",
            "Amapá",
            "Amazonas",
            "Bahia",
            "Ceará",
            "Distrito Federal",
            "Espírito Santo",
            "Goiás",
            "Maranhão",
            "Mato Grosso",
            "Mato Grosso do Sul",
            "Minas Gerais",
            "Pará",
            "Paraíba",
            "Paraná",
            "Pernambuco",
            "Piauí",
            "Rio de Janeiro",
            "Rio Grande do Norte",
            "Rio Grande do Sul",
            "Rondônia",
            "Roraima",
            "Santa Catarina",
            "São Paulo",
            "Sergipe",
            "Tocantins"
        )

        // seleciona o autocomplete (layout)
        val autoCompleteEstado = findViewById<AutoCompleteTextView>(R.id.Endereco_DropDowEstado_AutoComplete)

        // cria o adapter
        val adapterEstado = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, estado)

        // setar o nosso layout
        autoCompleteEstado.setAdapter(adapterEstado)

    }
}