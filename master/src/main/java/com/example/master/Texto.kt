package com.example.master

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_principal.*

class Texto : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_texto)


        var pantalla = intent.getIntExtra("pantalla",1)


        val titulo = findViewById<TextView>(R.id.titulo)
        val texto = findViewById<TextView>(R.id.texto)

        val volver = findViewById<Button>(R.id.volver)
        volver.setOnClickListener( View.OnClickListener {
            finish()
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out)
        })

        val continuar = findViewById<Button>(R.id.continuar)
        continuar.setOnClickListener( View.OnClickListener {
            if (pantalla==Sys.PANTALLA_TAREA_2){
                startActivity(Intent(this@Texto, Utilidades::class.java))
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
            }
            else {
                var i = Intent(this@Texto, Texto::class.java)
                i.putExtra("pantalla",pantalla+1)
                startActivity(i)
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
            }

        })
        if (pantalla==Sys.PANTALLA_TAREA_2){
            continuar.text=getString(R.string.utilidades)
        }


        Sys.setTitulo(pantalla, titulo, this@Texto)
        Sys.setTexto(pantalla, texto, this@Texto)

    }



}