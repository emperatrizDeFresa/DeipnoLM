package emperatriz.deipno.player

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Vibrator
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class Lupa : AppCompatActivity(), ZXingScannerView.ResultHandler, SensorEventListener, OnClickListener {
    override fun onClick(v: View?) {
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out)
    }

    var mScannerView: ZXingScannerView? = null
    var imgLupa: ImageView?=null;
    var txtQr: TextView?=null;
    private var mSensorManager: SensorManager? = null
    private var mLight: Sensor? = null
    private var torchOn: Boolean?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY))
        setContentView(R.layout.lupa);
        (findViewById(R.id.volver)as ImageView).setOnClickListener(this)
        mScannerView = findViewById(R.id.scanner) as ZXingScannerView
        mScannerView!!.setBorderAlpha(0f)
        val externalFont = Sys.getFont(this@Lupa)
        imgLupa = findViewById(R.id.imgLupa) as ImageView
        txtQr = findViewById(R.id.txtQr) as TextView

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.permission)
                    .setPositiveButton(R.string.continuar, DialogInterface.OnClickListener { dialog, id ->
                        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 12344)

                    })
            builder.create()
            builder.show()

        }


        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        mLight = mSensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)

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

        Sys.setButtonAnimation((findViewById(R.id.volver)as ImageView),anim, unanim)


    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                            .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                            .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                            .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                            .or(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY))
        }
    }

    override fun handleResult(p0: Result?) {
        mScannerView!!.resumeCameraPreview(this);
try {
    val data = Base64.decode(p0.toString(), Base64.DEFAULT)
//    imgLupa!!.setImageResource(R.drawable.lupa2)

    val status = Sys.checkPista(String(data), this)
    if (status==-1){
        Sys.reset(this)
        finish()
        val homeIntent = Intent(Intent.ACTION_MAIN, null)
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
        startActivity(homeIntent);
    } else if (status==-2) {
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out)
    } else if (status > 0) {
        val parts = String(data).split("_");
        val personaje = Integer.parseInt(parts[3])
        if (Sys.isVeladaIniciada(this@Lupa)) {
            if (personaje == Sys.WHITE) {

                var i = if (Sys.getVelada(this@Lupa) >= 4) Intent(this@Lupa, Juego::class.java) else Intent(this@Lupa, JuegoPuzzle::class.java)
                val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                v.vibrate(500)
                finish()
                val sdf = Sys.getPistaState(Sys.WHITE, this)
                if (Sys.getPistaState(Sys.WHITE, this) <= Sys.PISTA_CON_INDICIO) {
                    startActivity(i)
                    overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
                } else {
                    overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out)
                }

            } else {
                var i = Intent(this@Lupa, PistaEncontrada::class.java)
                if (status == Sys.PISTA_DESCUBIERTA) {
                    i.putExtra("text", getString(R.string.pistaDescubierta))
                } else if (status == Sys.PISTA_CON_INDICIO) {
                    i.putExtra("text", getString(R.string.indicioDescubierto).replace("@personaje@", Sys.getPersonajePista(personaje, this)))
                }
                val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                v.vibrate(500)
                finish()
                startActivity(i)
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        }
    } else {

    }
}catch (ex:Exception){

}
//        txtQr!!.text=String(data)
//        txtQr!!.visibility = View.VISIBLE
//        txtQr!!.animate().alpha(1f).setDuration(3000)
    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this)
        Handler().postDelayed(Runnable { mScannerView!!.startCamera() }, 300)
        mSensorManager!!.registerListener(this as SensorEventListener, mLight, SensorManager.SENSOR_DELAY_NORMAL)
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
        mSensorManager!!.unregisterListener(this as SensorEventListener);
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val luxTriggerUp = 10f
        val luxTriggerDown = 0.1f
        if (torchOn==null) torchOn=false
        if( event!!.sensor.getType() == Sensor.TYPE_LIGHT)
        {
            val currentLux = event!!.values[0]
            if (currentLux > luxTriggerUp && torchOn!!){
                mScannerView!!.flash=false
                torchOn=false
            }else if (currentLux < luxTriggerDown && !torchOn!!){
                mScannerView!!.flash=true
                torchOn=true
            }

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}