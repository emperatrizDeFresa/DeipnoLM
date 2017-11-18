package emperatriz.deipno.player

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.*

class Principal : AppCompatActivity(), View.OnClickListener {


    var buscarPista:ImageView?=null;
    var darPista:ImageView?=null;
    var miLibreta:ImageView?=null;
    var mapa:ImageView?=null;
    var verHistoria:ImageView?=null;
    var salir:ImageView?=null;

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                            .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                            .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                            .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                            .or(View.SYSTEM_UI_FLAG_IMMERSIVE))
        }
    }

    public override fun onResume() {
        super.onResume()
        if (Sys.getVelada(this)==-1){
            startActivity(Intent(this@Principal, Splash::class.java))
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out)
            finish()
        }
        mapa = findViewById(R.id.mapa) as ImageView
        darPista = findViewById(R.id.darPista) as ImageView
        miLibreta = findViewById(R.id.miLibreta) as ImageView
        if (Sys.isVeladaIniciada(this)){
            mapa!!.isEnabled=true
            mapa!!.setImageResource(R.drawable.vermapa)
            mapa!!.alpha=1f
            darPista!!.isEnabled=true
            darPista!!.setImageResource(R.drawable.mostrarpista)
            darPista!!.alpha=1f
            miLibreta!!.isEnabled=true
            miLibreta!!.setImageResource(R.drawable.verlibreta)
            miLibreta!!.alpha=1f
        }else{
            mapa!!.isEnabled=false
            mapa!!.setImageResource(R.drawable.vermapa2)
            mapa!!.alpha=0.4f
            darPista!!.isEnabled=false
            darPista!!.setImageResource(R.drawable.mostrarpista2)
            darPista!!.alpha=0.4f
            miLibreta!!.isEnabled=false
            miLibreta!!.setImageResource(R.drawable.verlibreta2)
            miLibreta!!.alpha=0.4f
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_IMMERSIVE))
        val externalFont = Sys.getFont(this@Principal)

        val tituloPersonaje = findViewById(R.id.tituloPersonaje) as TextView
        val descripcionPersonaje = findViewById(R.id.descripcionPersonaje) as TextView
        val fotoPersonaje = findViewById(R.id.imagenPersonaje) as ImageView
        buscarPista = findViewById(R.id.buscarPista) as ImageView
        buscarPista!!.setOnClickListener(this)
        mapa = findViewById(R.id.mapa) as ImageView
        darPista = findViewById(R.id.darPista) as ImageView
        miLibreta = findViewById(R.id.miLibreta) as ImageView
        darPista!!.setOnClickListener(this)

        miLibreta!!.setOnClickListener(this)

        mapa!!.setOnClickListener(this)
        verHistoria = findViewById(R.id.verHistoria) as ImageView
        verHistoria!!.setOnClickListener(this)
        salir = findViewById(R.id.salir) as ImageView
        salir!!.setOnClickListener(this)

        if (Sys.getPersonaje(this)==Sys.INVESTIGADOR){
            darPista!!.visibility = View.GONE
        }




        tituloPersonaje.typeface=externalFont
        descripcionPersonaje.typeface=externalFont



        Sys.setPersonajeTitulo(tituloPersonaje, this@Principal)
        Sys.setPersonajeDescripcion(descripcionPersonaje, this@Principal)
        Sys.setPersonajeFoto(fotoPersonaje, this@Principal)

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


        Sys.setButtonAnimation(darPista!!,anim, unanim)
        Sys.setButtonAnimation(buscarPista!!,anim, unanim)
        Sys.setButtonAnimation(miLibreta!!,anim, unanim)
        Sys.setButtonAnimation(mapa!!,anim, unanim)
        Sys.setButtonAnimation(verHistoria!!,anim, unanim)
        Sys.setButtonAnimation(salir!!,anim, unanim)


    }

    override fun onClick(v: View?) {
        if (v==verHistoria){
            startActivity(Intent(this@Principal, Historia::class.java))
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        } else if (v==buscarPista){
            startActivity(Intent(this@Principal, Lupa::class.java))
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }else if (v==miLibreta){
            startActivity(Intent(this@Principal, Libreta::class.java))
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }else if (v==darPista){
            startActivity(Intent(this@Principal, MostrarPista::class.java))
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }else if (v==salir){
            finish()
        }else if (v==mapa){
            startActivity(Intent(this@Principal, Mapa::class.java))
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }
    }



}