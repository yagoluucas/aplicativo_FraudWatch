package com.example.fraud_watch.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalDate

class User: Serializable {
    // lateinit serve para declarar uma variável que vai ser inicializada depois
    private lateinit var nome: String
    private lateinit var sobrenome: String
    private lateinit var cpf : String
    @RequiresApi(Build.VERSION_CODES.O)
    private lateinit var dataNascimento : LocalDate
    private lateinit var email: String
    private lateinit var telefone: String
    private lateinit var senha: String
}