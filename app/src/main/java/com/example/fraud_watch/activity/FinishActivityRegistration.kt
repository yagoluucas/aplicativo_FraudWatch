package com.example.fraud_watch.activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.fraud_watch.MainActivity
import com.example.fraud_watch.R
import com.example.fraud_watch.fragments.ArrowReturnCadastro
import com.example.fraud_watch.model.User
import com.example.fraud_watch.utils.Utils
import com.google.gson.Gson
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.io.BufferedWriter
import java.io.InputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.text.SimpleDateFormat
import java.util.concurrent.Executors

class FinishActivityRegistration: AppCompatActivity() {

    val utils: Utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_registration)

        val btnFinalizarCadastro: Button = findViewById(R.id.Finish_BtnFinalizarCadastro_Button)
        val user: User? = intent.getSerializableExtra("user") as? User
        val campoEmail: EditText = findViewById(R.id.Finish_CampoEmail_EditText)
        val campoTelefone: EditText = findViewById(R.id.Finish_CampoTelefone_EditText)
        val campoConfirmarSenha: EditText = findViewById(R.id.Finish_CampoComfirmarSenha_EditText)
        val campoSenha: EditText = findViewById(R.id.Finish_CampoSenha_EditText)

        val fragment = ArrowReturnCadastro.newInstance("finish_activity")
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container2, fragment)
            .commit()

        formatarCampoTelefone(campoTelefone)
        btnFinalizarCadastro.setOnClickListener{
            validaCampos(campoEmail, campoTelefone, campoSenha, campoConfirmarSenha)

            user?.email = campoEmail.text.toString()
            user?.senha = campoConfirmarSenha.text.toString()
            user?.telefone = campoTelefone.text.toString()

            enviarInformacoesCadastro(user)
        }
    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun validaCampos(campoEmail: EditText,
                     campoTelefone: EditText,
                     campoSenha: EditText,
                     campoConfirmarSenha: EditText): Boolean{

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(campoEmail.text.toString()).matches()){
            campoEmail.error = "Email inválido!"
            return false
        }

        if(campoTelefone.length() != 13){
            campoTelefone.error = "telefone inválido"
            return false
        }

        if(campoSenha.length() < 8){
            campoSenha.error = "A senha deve conter no mínimo 8 caracteres"
            return false
        }

        if(campoSenha.text.toString() != campoConfirmarSenha.text.toString()){
            campoConfirmarSenha.error = "Senhas não correspondem"
            return false
        }
        return true
    }


    fun formatarCampoTelefone(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false
            private val mascara = "## #####-####"
            private var oldText = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                if (isUpdating) {
                    return
                }

                // Remove qualquer caractere que não seja número
                val text = editable.toString().replace(Regex("[^\\d]"), "")
                var formattedText = ""

                var i = 0
                for (m in mascara.toCharArray()) {
                    if (m != '#' && i < text.length) {
                        formattedText += m
                    } else {
                        try {
                            formattedText += text[i]
                        } catch (e: Exception) {
                            break
                        }
                        i++
                    }
                }

                isUpdating = true
                oldText = formattedText
                editText.setText(formattedText)
                editText.setSelection(formattedText.length) // Move o cursor para o final
                isUpdating = false
            }
        })
    }

    fun enviarInformacoesCadastro(user: User?) {
        if (user == null) {
            Log.e("enviarInformacoesCadastro", "Usuário é nulo")
            return
        }

        // Se estiver usando o emulador, substitua "localhost" por "10.0.2.2"
        val url = URL("http://10.0.2.2:8080/usuario")
        val executorEmSegundoPlano = Executors.newSingleThreadExecutor()

        executorEmSegundoPlano.execute {
            var conexao: HttpURLConnection? = null
            try {
                user.cpf = user.cpf?.replace(Regex("[.]"), "")?.replace("-", "")
                user.telefone = user.telefone.replace(" ", "")
                val gson = Gson()
                val userJson = gson.toJson(user)

                // Abrir conexão
                conexao = url.openConnection() as HttpURLConnection
                conexao.requestMethod = "POST"
                conexao.doOutput = true
                conexao.setRequestProperty("Content-Type", "application/json; utf-8")
                conexao.setRequestProperty("Accept", "application/json")

                // Escrever o JSON no output stream
                val outputStream = conexao.outputStream
                val writer = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
                writer.write(userJson)
                writer.flush()
                writer.close()
                outputStream.close()

                // Enviar a requisição e obter a resposta
                val responseCode = conexao.responseCode

                var inputStream: InputStream
                if (responseCode in 200..299) {
                     runOnUiThread {
                         val intent = Intent(this, MainActivity::class.java)
                         object : CountDownTimer(5000, 1000){
                             override fun onTick(p0: Long) {
                                 val timer = p0.toString().substring(0, 1)
                                 utils.criarAlertDialog("Sucesso", "Você será redirecionado para a tela de login em ".plus(timer).plus(" segundos"), 5000, this@FinishActivityRegistration, R.drawable.check)
                             }

                             override fun onFinish() {
                                 startActivity(intent)
                             }

                         }.start()
                     }
                    inputStream = conexao.inputStream
                } else {
                    inputStream = conexao.errorStream
                    runOnUiThread {
                        utils.criarAlertDialog("Erro", inputStream.bufferedReader().use { it.readText() }, 5000, this, R.drawable.warning_circle)
                    }
                }

            } catch (e: Exception) {
                Log.e("enviarInformacoesCadastro", "Erro ao enviar informações: ${e.message}")
            } finally {
                conexao?.disconnect()
                executorEmSegundoPlano.shutdown()
            }
        }
    }
}