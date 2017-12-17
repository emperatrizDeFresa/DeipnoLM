package emperatriz.deipno.player

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import android.os.Build
import android.graphics.drawable.Drawable.ConstantState
import android.media.MediaPlayer
import android.os.Handler
import android.os.Vibrator
import android.view.KeyEvent
import android.view.MotionEvent
import java.util.*


class Juego : AppCompatActivity(), View.OnClickListener, View.OnTouchListener {
    var sequence = ArrayList<ImageView>()
    var leds = ArrayList<ImageView>()
    var step=0

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if ((event?.action ==  MotionEvent.ACTION_DOWN)){
            val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            v.vibrate(50)
        }
        return false
    }

    fun generateSequence(){
        sequence.add(b1!!)
        sequence.add(b2!!)
        sequence.add(b3!!)
        sequence.add(b4!!)
        sequence.add(b5!!)
        sequence.add(b6!!)
        sequence.add(b7!!)
        sequence.add(b8!!)
        sequence.add(b9!!)
        Collections.shuffle(sequence)
    }

    fun check(iv:ImageView):Boolean{
        if (sequence[step++]==iv){
            return true
        }else {
            step=0
            return false
        }
    }

    var b1:ImageView? = null
    var b2:ImageView? = null
    var b3:ImageView? = null
    var b4:ImageView? = null
    var b5:ImageView? = null
    var b6:ImageView? = null
    var b7:ImageView? = null
    var b8:ImageView? = null
    var b9:ImageView? = null

    private fun toggle(v:ImageView){
        val constantState: ConstantState

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            constantState = resources
                    .getDrawable(R.drawable.switch1, theme)
                    .getConstantState()
        } else {
            constantState = resources.getDrawable(R.drawable.switch1)
                    .getConstantState()
        }
        if (v.drawable.constantState==constantState){
            v.setImageResource(R.drawable.switch0)
        }else{
            v.setImageResource(R.drawable.switch1)
        }
    }

    var ledon:MediaPlayer? = null
    var lederror:MediaPlayer? = null

    fun paint(success:Boolean){
        if (ledon != null) {
            ledon!!.reset()
            ledon!!.release()
            ledon = MediaPlayer.create(this@Juego, R.raw.ledon)

        }else{
            ledon = MediaPlayer.create(this@Juego, R.raw.ledon)
        }
        if (lederror != null) {
            lederror!!.reset()
            lederror!!.release()
            lederror = MediaPlayer.create(this@Juego, R.raw.lederror)
        }else{
            lederror = MediaPlayer.create(this@Juego, R.raw.lederror)
        }
        for(i in 0..8){
            leds[i].setImageResource(R.drawable.ledoff)
        }
        if (success){
            ledon!!.start()
            for(i in 0..step-1){
                leds[i].setImageResource(R.drawable.ledon)
            }

        }else{
            lederror!!.start()
            for(i in 0..8){
                leds[i].setImageResource(R.drawable.ledno)
            }



            val handler = Handler()
            handler.postDelayed(Runnable {
                for(i in 0..8){
                    leds[i].setImageResource(R.drawable.ledoff)
                }
            }, 200)
        }
    }

    override fun onClick(v: View?) {
        toggle(v as ImageView)
        fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) +  start
        val n = (0..8).random()
        var r = sequence[n]
        if (r==v){
            r = sequence[(n+5)%8]
        }
        toggle(r)

        if (check(v)){
            paint(true)
            if (step==9){
                Sys.setPistaState(Sys.WHITE, Sys.PISTA_DESCUBIERTA, this)
                Sys.addConfirmations(Sys.WHITE,this)
                var i = Intent(this@Juego, PistaEncontrada::class.java)
                i.putExtra("text", getString(R.string.pistaDescubierta))
                val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                v.vibrate(500)
                finish()
                startActivity(i)
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        }else{
            paint(false)
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_IMMERSIVE))
        val externalFont = Sys.getFont(this@Juego)

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

        val l1 = findViewById(R.id.led1) as ImageView
        val l2 = findViewById(R.id.led2) as ImageView
        val l3 = findViewById(R.id.led3) as ImageView
        val l4 = findViewById(R.id.led4) as ImageView
        val l5 = findViewById(R.id.led5) as ImageView
        val l6 = findViewById(R.id.led6) as ImageView
        val l7 = findViewById(R.id.led7) as ImageView
        val l8 = findViewById(R.id.led8) as ImageView
        val l9 = findViewById(R.id.led9) as ImageView
        leds.add(l1)
        leds.add(l2)
        leds.add(l3)
        leds.add(l4)
        leds.add(l5)
        leds.add(l6)
        leds.add(l7)
        leds.add(l8)
        leds.add(l9)


        b1 = findViewById(R.id.switch1) as ImageView
        b2 = findViewById(R.id.switch2) as ImageView
        b3 = findViewById(R.id.switch3) as ImageView
        b4 = findViewById(R.id.switch4) as ImageView
        b5 = findViewById(R.id.switch5) as ImageView
        b6 = findViewById(R.id.switch6) as ImageView
        b7 = findViewById(R.id.switch7) as ImageView
        b8 = findViewById(R.id.switch8) as ImageView
        b9 = findViewById(R.id.switch9) as ImageView
        b1!!.setOnClickListener(this)
        b2!!.setOnClickListener(this)
        b3!!.setOnClickListener(this)
        b4!!.setOnClickListener(this)
        b5!!.setOnClickListener(this)
        b6!!.setOnClickListener(this)
        b7!!.setOnClickListener(this)
        b8!!.setOnClickListener(this)
        b9!!.setOnClickListener(this)
        b1!!.setOnTouchListener(this)
        b2!!.setOnTouchListener(this)
        b3!!.setOnTouchListener(this)
        b4!!.setOnTouchListener(this)
        b5!!.setOnTouchListener(this)
        b6!!.setOnTouchListener(this)
        b7!!.setOnTouchListener(this)
        b8!!.setOnTouchListener(this)
        b9!!.setOnTouchListener(this)

        generateSequence()

    }



}