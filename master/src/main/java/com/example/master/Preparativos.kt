package com.example.master

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.ImageView

class Preparativos : AppCompatActivity(), View.OnClickListener {


    var p2: Button?=null;
    var p3: Button?=null;
    var p4: Button?=null;
    var p5: Button?=null;
    var volver: Button?=null;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preparativos)

        p2 = findViewById<Button>(R.id.p2)
        p2!!.setOnClickListener(this)
        p3 = findViewById<Button>(R.id.p3)
        p3!!.setOnClickListener(this)
        p4 = findViewById<Button>(R.id.p4)
        p4!!.setOnClickListener(this)
        p5 = findViewById<Button>(R.id.p5)
        p5!!.setOnClickListener(this)
        volver = findViewById<Button>(R.id.volver)
        volver!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v==p2){
            var i =Intent(this@Preparativos, Texto::class.java)
            i.putExtra("pantalla",Sys.PANTALLA_BIENVENIDA)
            startActivity(i)
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        } else  if (v==p3){
            var i =Intent(this@Preparativos, Texto::class.java)
            i.putExtra("pantalla",Sys.PANTALLA_DESCRIPCION)
            startActivity(i)
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }else  if (v==p4){
            var i =Intent(this@Preparativos, Texto::class.java)
            i.putExtra("pantalla",Sys.PANTALLA_VELADA)
            startActivity(i)
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }else  if (v==p5){
            startActivity(Intent(this@Preparativos, Tareas::class.java))
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }else  if (v==volver){
            finish()
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out)
        }
    }



}