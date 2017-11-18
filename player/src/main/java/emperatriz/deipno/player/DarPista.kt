package emperatriz.deipno.player

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.google.zxing.qrcode.encoder.QRCode
import java.security.SecureRandom
import java.text.SimpleDateFormat
import java.util.*

class DarPista : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_darpista)
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_IMMERSIVE))
        val externalFont = Sys.getFont(this@DarPista)

        val qr = findViewById(R.id.qr) as ImageView


        val volver = findViewById(R.id.volver) as ImageView
        volver.setOnClickListener( View.OnClickListener {
            finish()
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        })




        val mmdf = SimpleDateFormat("SSS")
        val qrText = String(Base64.encode(("deipno_pista_2_"+Sys.getPersonaje(this)+"_"+mmdf.format(Date())).toByteArray(), Base64.DEFAULT)).replace("\n","")
        val bitmap = net.glxn.qrgen.android.QRCode.from(qrText).withSize(1000, 1000).withErrorCorrection(ErrorCorrectionLevel.H)
                                                                        .withColor(0xff000000.toInt(),0xffffffff.toInt()).bitmap()
        qr.setImageBitmap(bitmap)

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