package com.example.fraud_watch.model

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.annotations.JsonAdapter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.io.Serializable
import java.lang.reflect.Type

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

    var cpf: String?
        get() = field
        set(value) {
            field = value
        }

    @JsonAdapter(LocalDateSerializer::class)
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

    var endereco: Address?
        get() = field
        set(value) {
            field = value
        }

    var tipoUsuarioid: Int
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
        this.tipoUsuarioid = 2
        this.endereco = address
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
        return "User(nome='$nome', sobrenome='$sobrenome', cpf='$cpf', dataNascimento=$dataNascimento, email='$email', tipoUsuarioid='$tipoUsuarioid'  telefone='$telefone', senha='$senha', address=$endereco)"
    }
}

class LocalDateSerializer : JsonSerializer<LocalDate> {
    override fun serialize(src: LocalDate?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
    }
}
