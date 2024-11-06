package com.example.consumerviacep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt = findViewById<Button>(R.id.btnSend)
        val et = findViewById<EditText>(R.id.etCep)

        bt.setOnClickListener{
            if(et.text.toString().length == 8) {
                val intent = Intent(this, RespActivity::class.java)
                intent.putExtra("cep", et.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "O CEP precisa conter 8 dígitos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

//* build.gradle module -> compilesdk = 34
//1- ir no activity_main.xml, clicar no code
//2- apagar o textview inteiro
//3- colocar os componentes

//4- file / new / activity / empty view activity   nome RespActivity
//5- ir no activity_resp.xml, no code
//6- colocar os componentes

//7- aqui colocar os códigos

//8- imports no build.gradle

//9- adicionar permissao no androidmanifest

//10- no RespActivity, instrucoes la