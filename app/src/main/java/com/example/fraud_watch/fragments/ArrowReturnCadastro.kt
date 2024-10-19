package com.example.fraud_watch.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.fraud_watch.R
import com.example.fraud_watch.activity.AddressActivity
import com.example.fraud_watch.activity.PersonalInfoActivity

class ArrowReturnCadastro: Fragment() {

    companion object {
        fun newInstance(nomeActivity: String): ArrowReturnCadastro {
            val fragment = ArrowReturnCadastro()
            val args = Bundle()
            args.putString("nomeActivity", nomeActivity)
            fragment.arguments = args
            return fragment
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.btn_voltar_cadastro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnVoltarCadastro: AppCompatButton = view.findViewById(R.id.Voltar_Cadastro)
        btnVoltarCadastro.setOnClickListener{
            val nomeActivity: String? = arguments?.getString("nomeActivity")

            val intent = if (nomeActivity == "address")
                Intent(activity, PersonalInfoActivity::class.java)
            else
                Intent(activity, AddressActivity::class.java)

            startActivity(intent)
        }
    }
}