package com.example.fraud_watch.model

import java.io.Serializable

class Address : Serializable {

    var cep: String
        get() = field
        set(value) {
            field = value
        }

    var estado: String
        get() = field
        set(value) {
            field = value
        }

    var cidade: String
        get() = field
        set(value) {
            field = value
        }

    var bairro: String
        get() = field
        set(value) {
            field = value
        }

    var rua: String
        get() = field
        set(value) {
            field = value
        }

    var numero: String
        get() = field
        set(value) {
            field = value
        }

    var complemento: String = ""
        get() = field
        set(value) {
            field = value
        }

    // Construtor principal com todos os atributos
    constructor(
        cep: String,
        estado: String,
        cidade: String,
        bairro: String,
        rua: String,
        numero: String,
        complemento: String = ""
    ) {
        this.cep = cep
        this.estado = estado
        this.cidade = cidade
        this.bairro = bairro
        this.rua = rua
        this.numero = numero
        this.complemento = complemento
    }

    // Construtor secundário para inicializar sem complemento
    constructor(
        cep: String,
        estado: String,
        cidade: String,
        bairro: String,
        rua: String,
        numero: String
    ) : this(cep, estado, cidade, bairro, rua, numero, complemento = "")

    override fun toString(): String {
        return "Address(cep='$cep', estado='$estado', cidade='$cidade', bairro='$bairro', rua='$rua', numero='$numero', complemento='$complemento')"
    }
}
