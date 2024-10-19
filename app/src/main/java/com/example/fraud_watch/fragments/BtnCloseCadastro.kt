package com.example.fraud_watch.fragments


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.fraud_watch.MainActivity
import com.example.fraud_watch.R

class BtnCloseCadastro: Fragment() {

    private lateinit var btnFecharCadastrar: AppCompatButton

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_btn_fechar_cadastro, container, false)
        btnFecharCadastrar = view.findViewById(R.id.Cadastro_Fechar)
        btnFecharCadastrar.setOnClickListener(){
            val intent = Intent(activity, MainActivity::class.java)
            activity?.startActivity(intent)
        }
        return view
    }
}