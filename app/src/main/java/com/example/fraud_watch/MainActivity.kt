package com.example.fraud_watch


import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fraud_watch.activity.HomeActivity
import com.example.fraud_watch.activity.PersonalInfoActivity
import com.example.fraud_watch.utils.Utils
import com.google.gson.Gson
import java.io.BufferedWriter
import java.io.InputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private val utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val campoEmail = findViewById<EditText>(R.id.LOGIN_CampoEmail_editTextTextEmailAddress)
        val campoSenha = findViewById<EditText>(R.id.LOGIN_CampoSenha_editTextText)
        val btnEntrar = findViewById<Button>(R.id.LOGIN_BtnEntrar_Button)

        val btnCriarConta = findViewById<Button>(R.id.LOGIN_BtnCriarConta_Button)
        btnCriarConta.setOnClickListener{
            val intent = Intent(this, PersonalInfoActivity::class.java)
            startActivity(intent)
        }

        btnEntrar.setOnClickListener{
            val emailEstaVazio: Boolean = campoEmail.text.toString() == ""
            val senhaEstaVazia: Boolean = campoSenha.text.toString() == ""
            if(emailEstaVazio) campoEmail.error = "informe o seu email"
            if(senhaEstaVazia) campoSenha.error = "informe a sua senha"
            if(!emailEstaVazio && !senhaEstaVazia) conferirCredenciaisLogin(campoEmail.text.toString(), campoSenha.text.toString())
        }
    }

    fun conferirCredenciaisLogin(email: String, senha: String) {

        var url = URL("http://10.0.2.2:8080/usuario/login")
        val executorSegundoPlano = Executors.newSingleThreadExecutor()

        executorSegundoPlano.execute {
            var conexao: HttpURLConnection? = null

            try{
                val gson = Gson()
                val loginJson = gson.toJson(mapOf("email" to email, "senha" to senha))

                // abrir conexão
                conexao = url.openConnection() as HttpURLConnection
                conexao.requestMethod = "POST"
                conexao.doOutput
                conexao.setRequestProperty("Content-Type", "application/json; utf-8")
                conexao.setRequestProperty("Accept", "application/json")

                // Escrever o JSON no output stream
                val outputStream = conexao.outputStream
                val writer = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
                writer.write(loginJson)
                writer.flush()
                writer.close()
                outputStream.close()

                // enviar a requisição

                val responseCode = conexao.responseCode

                var inputStream: InputStream
                if (responseCode in 200..299) {

                    runOnUiThread {
                        object : CountDownTimer(2000, 1000) {
                            override fun onTick(p0: Long) {
                                utils.criarAlertDialog(
                                    "Sucesso",
                                    "Login efetuado com sucesso",
                                    2000,
                                    this@MainActivity,
                                    R.drawable.check
                                )
                            }

                            override fun onFinish() {
                                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                                startActivity(intent)
                            }

                        }.start()
                    }

                    inputStream = conexao.inputStream
                } else {
                    inputStream = conexao.errorStream
                    runOnUiThread {
                        utils.criarAlertDialog("Falha", inputStream.bufferedReader().use { it.readText() }, 5000, this, R.drawable.warning_circle)
                    }
                }
            }catch (e: Exception){
                Log.v("Erro: ", e.toString())
                utils.criarAlertDialog("Erro", "No momento não foi possível fazer o login", 5000, this, R.drawable.warning_circle)
            }finally {
                conexao?.disconnect()
                executorSegundoPlano.shutdown()
            }
        }
    }
}