package com.example.master

import android.content.Context
import android.graphics.*
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by ramon on 3/04/18.
 */
object Sys {
    val prefName="deipnoMaster"

    public val WHITE=1
    public val BROWN=2
    public val GRAY=3
    public val NAVY=4
    public val GOLDEN=5
    public val VIOLET=6
    public val BLACK=7
    public val GREEN=8
    public val MUERTO=9
    public val INVESTIGADOR=10


    public val PANTALLA_BIENVENIDA=1
    public val PANTALLA_DESCRIPCION=2
    public val PANTALLA_VELADA=3
    public val PANTALLA_TAREA_1=4
    public val PANTALLA_TAREA_2=5
    public val PANTALLA_DIA_VELADA=6

    fun reset(context: Context){
        val prefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        prefs.edit().clear().commit()
    }



    fun setTitulo(pantalla : Int, v : TextView, context : Context) {
        when (pantalla){
            PANTALLA_BIENVENIDA -> v.text = context.getString(R.string.titulo1)
            PANTALLA_DESCRIPCION -> v.text = context.getString(R.string.titulo2)
            PANTALLA_VELADA -> v.text = context.getString(R.string.titulo3)
            PANTALLA_TAREA_1 -> v.text = context.getString(R.string.titulo51)
            PANTALLA_TAREA_2 -> v.text = context.getString(R.string.titulo52)
            PANTALLA_DIA_VELADA -> v.text = context.getString(R.string.tituloDiaVelada)
        }
    }

    fun setTexto(pantalla : Int, v : TextView, context : Context) {
        when (pantalla){
            PANTALLA_BIENVENIDA -> v.text = context.getString(R.string.texto1)
            PANTALLA_DESCRIPCION -> v.text = context.getString(R.string.texto2)
            PANTALLA_VELADA -> v.text = context.getString(R.string.texto3)
            PANTALLA_TAREA_1 -> v.text = context.getString(R.string.texto51)
            PANTALLA_TAREA_2 -> v.text = context.getString(R.string.texto52)
            PANTALLA_DIA_VELADA -> v.text = context.getString(R.string.textoDiaVelada)
        }
    }


}