package com.example.fraud_watch.utils

class Utils {
    fun validaCpf(cpf: String): Boolean{
        val regex = "[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}".toRegex()
        return regex.matches(cpf)
    }

    fun validaCampoNome(texto: String): Boolean{
        val regex = "^[A-ZÀ-Ÿa-zà-ÿ]+(?:\\s[A-ZÀ-Ÿa-zà-ÿ]+)*\$".toRegex()
        return regex.matches(texto)
    }

}