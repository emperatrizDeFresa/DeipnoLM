package emperatriz.deipno.player

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class JuegoPuzzle : AppCompatActivity() {

    private var view: SlidePuzzleView? = null
    private var slidePuzzle = SlidePuzzle()
    private var expert: Boolean = false

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puzzle)
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_IMMERSIVE))
        val externalFont = Sys.getFont(this@JuegoPuzzle)

        var container = findViewById(R.id.puzzleContainer) as LinearLayout
        view = SlidePuzzleView(this, slidePuzzle)
        view!!.bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.white)
        container.addView(view)

        shuffle()

        val textoJuego = findViewById(R.id.textoJuego) as TextView

        val volver = findViewById(R.id.volver) as ImageView
        volver.setOnClickListener( View.OnClickListener {
            finish()
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        })

        textoJuego.typeface=externalFont

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

    }

    private fun shuffle() {
        slidePuzzle.init(3,3)
        slidePuzzle.shuffle()
        view!!.invalidate()
        expert = view!!.showNumbers === SlidePuzzleView.ShowNumbers.NONE
    }

    public fun onFinish(){
        Sys.setPistaState(Sys.WHITE, Sys.PISTA_DESCUBIERTA, this)
        Sys.addConfirmations(Sys.WHITE,this)
        var i = Intent(this@JuegoPuzzle, PistaEncontrada::class.java)
        i.putExtra("text", getString(R.string.pistaDescubierta))
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        v.vibrate(500)
        finish()
        startActivity(i)
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }


    public fun playSound(){
    }

}