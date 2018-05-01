package com.example.master

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.ImageView

class Tareas : AppCompatActivity(), View.OnClickListener {

    var p51: Button?=null;
    var p52: Button?=null;
    var p53: Button?=null;
    var volver: Button?=null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tareas)

        p51 = findViewById<Button>(R.id.p51)
        p51!!.setOnClickListener(this)
        p52 = findViewById<Button>(R.id.p52)
        p52!!.setOnClickListener(this)
        p53 = findViewById<Button>(R.id.p53)
        p53!!.setOnClickListener(this)
        volver = findViewById<Button>(R.id.volver)
        volver!!.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v==p51){
            var i = Intent(this@Tareas, Texto::class.java)
            i.putExtra("pantalla", Sys.PANTALLA_TAREA_1)
            startActivity(i)
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        } else  if (v==p52){
            var i = Intent(this@Tareas, Texto::class.java)
            i.putExtra("pantalla", Sys.PANTALLA_TAREA_2)
            startActivity(i)
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }else  if (v==volver){
            finish()
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out)
        }
    }



}