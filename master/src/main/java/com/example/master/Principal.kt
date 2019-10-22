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
import com.example.master.R.id.preparativos

class Principal : AppCompatActivity(), View.OnClickListener {


    var preparativos: Button?=null;
    var previo: Button?=null;
    var diaVelada: Button?=null;
    var creacionVelada: Button?=null;
    var utilidades: Button?=null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        preparativos = findViewById<Button>(R.id.preparativos)
        preparativos!!.setOnClickListener(this)
        previo = findViewById<Button>(R.id.previo)
        previo!!.setOnClickListener(this)
        diaVelada = findViewById<Button>(R.id.diaVelada)
        diaVelada!!.setOnClickListener(this)
        creacionVelada = findViewById<Button>(R.id.creacionVelada)
        creacionVelada!!.setOnClickListener(this)
        utilidades = findViewById<Button>(R.id.utilidades)
        utilidades!!.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v==previo){
            startActivity(Intent(this@Principal, Preparativos::class.java))
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }else if (v==utilidades){
            startActivity(Intent(this@Principal, Utilidades::class.java))
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }else if (v==preparativos){
            var i =Intent(this@Principal, Texto::class.java)
            i.putExtra("pantalla",Sys.PANTALLA_TAREA_2)
            startActivity(i)
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }else if (v==diaVelada){
            var i =Intent(this@Principal, Texto::class.java)
            i.putExtra("pantalla",Sys.PANTALLA_DIA_VELADA)
            startActivity(i)
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }
    }



}