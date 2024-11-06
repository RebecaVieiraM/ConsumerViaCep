package com.example.consumerviacep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class RespActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resp)
        intent.getStringExtra("cep")?.let { getCep(it) }

        val bt = findViewById<Button>(R.id.btnClose)
        bt.setOnClickListener{
            finish()
        }
    }

    private fun getCep(cep: String){
        val urlApi = "https://viacep.com.br/ws/$cep/json/"

        GlobalScope.launch {
            val url = URL(urlApi)

            val urlConnection = url.openConnection() as HttpURLConnection

            urlConnection.connectTimeout = 7000

            val content: String = urlConnection.inputStream.bufferedReader().use(BufferedReader::readText)

            val json = JSONObject(content)

            val pbCep = findViewById<ProgressBar>(R.id.pbCep)
            val tvCep = findViewById<TextView>(R.id.tvResp)

            runOnUiThread{
                Animacao().tradeView(pbCep, tvCep)

                if(json.has("erro")){
                    tvCep.text = "Cep inválido ou consulta inválida!"
                }else {
                    val cep = json.getString("cep")
                    val rua = json.getString("logradouro")
                    val bairro = json.getString("bairro")
                    val cidade = json.getString("localidade")
                    val estado = json.getString("uf")
                    tvCep.text = "CEP: $cep\nRUA-AV.: $rua\nBairro: $bairro\nCidade: $cidade\nEstado: $estado"
                }
            }
        }
    }

    class Animacao {
        private fun fadeIn(view: View){
            val animacao = AlphaAnimation(0f, 1f)
            animacao.duration = 500L
            view.startAnimation(animacao)
        }
        private fun fadeOut(view: View){
            val animacao = AlphaAnimation(1f, 0f)
            animacao.duration = 500L
            view.startAnimation(animacao)
        }

        public fun tradeView(primeiraView: View, segundaView: View){
            fadeOut(primeiraView)
            Handler().postDelayed({
                primeiraView.visibility = View.INVISIBLE
                segundaView.visibility = View.VISIBLE
                fadeIn(segundaView)
            }, 500L)
        }
    }
}

//11- colocar os codigos