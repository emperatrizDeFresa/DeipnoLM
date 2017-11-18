package emperatriz.deipno.player

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import android.view.SurfaceHolder
import android.view.SurfaceView



class Mapa : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_IMMERSIVE))
        val externalFont = Sys.getHandwrittenFont(this@Mapa)


        val volver = findViewById(R.id.volver) as ImageView
        volver.setOnClickListener( View.OnClickListener {
            finish()
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        })

        val libreta = findViewById(R.id.miLibreta) as ImageView
        libreta.setOnClickListener( View.OnClickListener {
            finish()
            startActivity(Intent(this@Mapa, Libreta::class.java))
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        })

        val anim = ScaleAnimation(
                1f, 0.8f, // Start and end values for the X axis scaling
                1f, 0.8f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f) // Pivot point of Y scaling
        anim.fillAfter = true // Needed to keep the result of the animation
        anim.duration = 150

        val unanim = ScaleAnimation(
                0.8f, 1f, // Start and end values for the X axis scaling
                0.8f, 1f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f) // Pivot point of Y scaling
        anim.fillAfter = true // Needed to keep the result of the animation
        anim.duration = 150

        Sys.setButtonAnimation(volver!!, anim, unanim)
        Sys.setButtonAnimation(libreta!!, anim, unanim)

        val surface = findViewById(R.id.surfaceView) as SurfaceView
        surface.holder.addCallback(object : SurfaceHolder.Callback {

            override fun surfaceCreated(holder: SurfaceHolder) {
                val handler = Handler()
                handler.postDelayed(Runnable {
                    drawAll(holder)
                }, 100)

            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {}

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
        })




    }

    fun drawAll(holder: SurfaceHolder){
        val canvas = holder.lockCanvas()

        Sys.canvas=canvas
        Sys.paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.mapa),null, canvas.clipBounds,Sys.paint)
        Sys.drawPersonaje(Sys.WHITE,this)
        Sys.drawPersonaje(Sys.BROWN,this)
        Sys.drawPersonaje(Sys.GRAY,this)
        Sys.drawPersonaje(Sys.NAVY,this)
        Sys.drawPersonaje(Sys.GOLDEN,this)
        Sys.drawPersonaje(Sys.VIOLET,this)
        Sys.drawPersonaje(Sys.BLACK,this)
        Sys.drawPersonaje(Sys.GREEN,this)
        Sys.drawPersonaje(Sys.MUERTO,this)
//        Sys.drawPersonajeTest(Sys.GOLDEN, Sys.recibidor1, this)
//        Sys.drawPersonajeTest(Sys.GOLDEN, Sys.cocina1, this)
//        Sys.drawPersonajeTest(Sys.GOLDEN, Sys.cocina2, this)
//        Sys.drawPersonajeTest(Sys.GOLDEN, Sys.bano1, this)
//        Sys.drawPersonajeTest(Sys.GOLDEN, Sys.bano2, this)
//        Sys.drawPersonajeTest(Sys.GOLDEN, Sys.dormitorio1, this)
//        Sys.drawPersonajeTest(Sys.GOLDEN, Sys.dormitorio2, this)
//        Sys.drawPersonajeTest(Sys.GOLDEN, Sys.salon1, this)
//        Sys.drawPersonajeTest(Sys.GOLDEN, Sys.salon2, this)
//        Sys.drawPersonajeTest(Sys.GOLDEN, Sys.despacho1, this)
//        Sys.drawPersonajeTest(Sys.GOLDEN, Sys.despacho2, this)
//        Sys.drawPersonajeTest(Sys.GOLDEN, Sys.pasillo, this)

        holder.unlockCanvasAndPost(canvas)
    }


}