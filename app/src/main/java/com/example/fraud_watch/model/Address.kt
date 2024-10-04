package com.example.fraud_watch.model

import java.io.Serializable

class Address: Serializable {
    private lateinit var cep: String
    private lateinit var estado: String
    private lateinit var cidade: String
    private lateinit var bairro: String
    private lateinit var rua: String
    private lateinit var numero: String
    private lateinit var complemento: String

    constructor(cep: String, estado: String, cidade: String, bairro: String, rua: String, numero: String) {
        this.cep = cep
        this.estado = estado
        this.cidade = cidade
        this.bairro = bairro
        this.rua = rua
        this.numero = numero
    }
}