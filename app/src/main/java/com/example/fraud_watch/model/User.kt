package com.example.fraud_watch.model

import org.threeten.bp.LocalDate
import java.io.Serializable

class User : Serializable {

    var nome: String
        get() = field
        set(value) {
            field = value
        }

    var sobrenome: String
        get() = field
        set(value) {
            field = value
        }

    var cpf: String
        get() = field
        set(value) {
            field = value
        }

    var dataNascimento: LocalDate
        get() = field
        set(value) {
            field = value
        }

    var email: String
        get() = field
        set(value) {
            field = value
        }

    var telefone: String
        get() = field
        set(value) {
            field = value
        }

    var senha: String
        get() = field
        set(value) {
            field = value
        }

    var address: Address?
        get() = field
        set(value) {
            field = value
        }

    constructor(
        nome: String,
        sobrenome: String,
        cpf: String,
        dataNascimento: LocalDate,
        email: String = "",
        telefone: String = "",
        senha: String = "",
        address: Address? = null
    ) {
        this.nome = nome
        this.sobrenome = sobrenome
        this.cpf = cpf
        this.dataNascimento = dataNascimento
        this.email = email
        this.telefone = telefone
        this.senha = senha
        this.address = address
    }

    constructor(nome: String, sobrenome: String, cpf: String, dataNascimento: LocalDate) : this(
        nome,
        sobrenome,
        cpf,
        dataNascimento,
        email = "",
        telefone = "",
        senha = "",
        address = null
    )

    override fun toString(): String {
        return "User(nome='$nome', sobrenome='$sobrenome', cpf='$cpf', dataNascimento=$dataNascimento, email='$email', telefone='$telefone', senha='$senha', address=$address)"
    }
}