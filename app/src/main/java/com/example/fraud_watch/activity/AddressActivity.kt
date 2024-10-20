package com.example.fraud_watch.activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.JsonReader

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.fraud_watch.R
import com.example.fraud_watch.fragments.ArrowReturnCadastro
import com.example.fraud_watch.fragments.BottomNavigationMenu
import com.example.fraud_watch.model.Address
import com.example.fraud_watch.model.User
import com.example.fraud_watch.utils.Utils
import java.io.InputStreamReader
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class AddressActivity: AppCompatActivity() {

    private var regiao: String = ""

    private val utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        // esta passando o nome corretamente agora
        val fragment = ArrowReturnCadastro.newInstance("address")
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container2, fragment)
            .commit()


        val btnFinalizarCadastro: Button = findViewById(R.id.Endereco_BtnAvancar_Button)
        val user: User? = intent.getSerializableExtra("user") as? User
        val campoCep: EditText = findViewById(R.id.Endereco_CampoCep_EditText)
        val campoBairro: EditText = findViewById(R.id.Endereco_CampoBairro_EditText)
        val campoCidade: EditText = findViewById(R.id.Endereco_CampoCidade_EditText)
        val campoEstado: EditText = findViewById(R.id.Endereco_CampoEstado_EditText)
        val campoRua: EditText = findViewById(R.id.Endereco_CampoRua_EditText)
        val campoNumero: EditText = findViewById(R.id.Endereco_CampoNumero_EditText)
        val campoComplemento: EditText = findViewById(R.id.Endereco_CampoComplemento_EditText)

        formatarCampoCEP(campoCep)

        campoCep.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0?.length == 9) {
                    object : CountDownTimer(500, 500){
                        override fun onTick(p0: Long) {

                        }

                        override fun onFinish() {
                            funcaoBuscarCep(campoCep.text.toString(), campoRua, campoBairro, campoCidade, campoEstado)
                        }

                    }.start()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        btnFinalizarCadastro.setOnClickListener{
            val endereco = Address(
                campoCep.text.toString(),
                campoEstado.text.toString(),
                campoCidade.text.toString(),
                campoBairro.text.toString(),
                campoRua.text.toString(),
                campoNumero.text.toString(),
                regiao,
                campoComplemento.text.toString())

            user?.endereco = endereco
            val intent = Intent(this, FinishActivityRegistration::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun formatarCampoCEP(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false
            private val mascara = "#####-###"
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

    fun funcaoBuscarCep(cep: String,
                        campoRua: EditText,
                        campoBairro: EditText,
                        campoCidade: EditText,
                        campoEstado: EditText){
        val url = URL("https://viacep.com.br/ws/"+  cep + "/json")

        // execução em segundo plano

        val executorEmSegundoPlano = Executors.newSingleThreadExecutor()
        executorEmSegundoPlano.execute{
            // abre a conexão com a api
            val conexao = url.openConnection() as HttpsURLConnection

            // define o tipo de método
            conexao.requestMethod = "GET"

            if(conexao.responseCode == HttpsURLConnection.HTTP_OK){
                val streamEntrada = conexao.inputStream
                val leitorJson = JsonReader(InputStreamReader(streamEntrada, "UTF-8"))
                leitorJson.beginObject()
                if(leitorJson.hasNext()){
                    leitorJson.nextName()
                    leitorJson.nextString()
                    leitorJson.nextName()
                    val rua = leitorJson.nextString()
                    leitorJson.nextName()
                    leitorJson.nextString()
                    leitorJson.nextName()
                    leitorJson.nextString()
                    leitorJson.nextName()
                    val bairro = leitorJson.nextString()
                    leitorJson.nextName()
                    val cidade = leitorJson.nextString()
                    leitorJson.nextName()
                    leitorJson.nextString()
                    leitorJson.nextName()
                    val estado = leitorJson.nextString()
                    leitorJson.nextName()
                    regiao = leitorJson.nextString()
                    runOnUiThread{
                        campoRua.setText(rua)
                        campoBairro.setText(bairro)
                        campoCidade.setText(cidade)
                        campoEstado.setText(estado)
                    }
                }

            }else{
                utils.criarAlertDialog("Não encontrado", "O cep " + cep + "informado não foi localizado",5000, this, R.drawable.warning_circle)
            }
        }

        // fechando a conexão
        executorEmSegundoPlano.shutdown()
    }

}