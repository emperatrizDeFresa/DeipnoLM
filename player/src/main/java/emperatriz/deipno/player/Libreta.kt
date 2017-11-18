package emperatriz.deipno.player

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class Libreta : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libreta)
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_IMMERSIVE))
        val externalFont = Sys.getHandwrittenFont(this@Libreta)

        val green = findViewById(R.id.green) as TextView
        val brown = findViewById(R.id.brown) as TextView
        val golden = findViewById(R.id.golden) as TextView
        val navy = findViewById(R.id.navy) as TextView
        val black = findViewById(R.id.black) as TextView
        val white = findViewById(R.id.white) as TextView
        val violet = findViewById(R.id.violet) as TextView
        val gray = findViewById(R.id.gray) as TextView
        val volver = findViewById(R.id.volver) as ImageView
        volver.setOnClickListener( View.OnClickListener {
            finish()
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        })
        val mapa = findViewById(R.id.mapa) as ImageView
        mapa.setOnClickListener( View.OnClickListener {
            finish()
            startActivity(Intent(this@Libreta, Mapa::class.java))
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        })


        green.typeface=externalFont
        brown.typeface=externalFont
        golden.typeface=externalFont
        navy.typeface=externalFont
        black.typeface=externalFont
        white.typeface=externalFont
        violet.typeface=externalFont
        gray.typeface=externalFont

        Sys.setPista(green,Sys.GREEN,this)
        Sys.setPista(brown,Sys.BROWN ,this)
        Sys.setPista(gray,Sys.GRAY  ,this)
        Sys.setPista(navy,Sys.NAVY  ,this)
        Sys.setPista(golden,Sys.GOLDEN,this)
        Sys.setPista(violet,Sys.VIOLET,this)
        Sys.setPista(black,Sys.BLACK ,this)
        Sys.setPista(white,Sys.WHITE ,this)


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
        Sys.setButtonAnimation(mapa!!,anim, unanim)



    }



}