package com.example.fraud_watch.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import com.example.fraud_watch.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Locale

class Utils {
    fun validaCpf(cpf: String): Boolean{
        val regex = "[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}".toRegex()
        return regex.matches(cpf)
    }

    fun validaCampoNome(texto: String): Boolean{
        val regex = "^[A-ZÀ-Ÿa-zà-ÿ]+(?:\\s[A-ZÀ-Ÿa-zà-ÿ]+)*\$".toRegex()
        return regex.matches(texto)
    }


    fun criarAlertDialog(titulo: String, mensagem: String, tempoMensagem: Long, context: Context, icon: Int){
        val alertDialog: AlertDialog = AlertDialog.Builder(context)
            .setTitle(titulo)
            .setMessage(mensagem)
            .setIcon(icon)
            .setCancelable(false)
            .show()

        // time para apagar o alert
        CoroutineScope(Dispatchers.Main).launch {
            delay(tempoMensagem)
            if(alertDialog.isShowing){
                alertDialog.dismiss()
            }
        }
    }
}