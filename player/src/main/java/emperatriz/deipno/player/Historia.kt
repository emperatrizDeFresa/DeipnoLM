package emperatriz.deipno.player

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class Historia : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historia)
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_IMMERSIVE))
        val externalFont = Sys.getFont(this@Historia)

        val tituloPersonaje = findViewById(R.id.tituloPersonaje) as TextView
        val descripcionPersonaje = findViewById(R.id.descripcionPersonaje) as TextView
        val indumentaria = findViewById(R.id.indumentaria) as TextView
        val indumentariaPersonaje = findViewById(R.id.indumentariaPersonaje) as TextView
        val comportamiento = findViewById(R.id.comportamiento) as TextView
        val comportamientoPersonaje = findViewById(R.id.comportamientoPersonaje) as TextView
        val tarea = findViewById(R.id.tarea) as TextView
        val tareaPersonaje = findViewById(R.id.tareaPersonaje) as TextView
        val fotoPersonaje = findViewById(R.id.imagenPersonaje) as ImageView
        val volver = findViewById(R.id.volver) as ImageView
        volver.setOnClickListener( View.OnClickListener {
            finish()
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        })


        tituloPersonaje.typeface=externalFont
        descripcionPersonaje.typeface=externalFont
        indumentaria.typeface=externalFont
        indumentariaPersonaje.typeface=externalFont
        comportamiento.typeface=externalFont
        comportamientoPersonaje.typeface=externalFont
        tarea.typeface=externalFont
        tareaPersonaje.typeface=externalFont

        Sys.setPersonajeTitulo(tituloPersonaje, this@Historia)
        Sys.setPersonajeDescripcion(descripcionPersonaje, this@Historia)
        Sys.setPersonajeIndumentaria(indumentariaPersonaje, this@Historia)
        Sys.setPersonajeComportamiento(comportamientoPersonaje, this@Historia)
        Sys.setPersonajeTarea(tareaPersonaje, this@Historia)
        Sys.setPersonajeFoto(fotoPersonaje, this@Historia)

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

        Sys.setButtonAnimation(volver!!,anim, unanim)




    }



}