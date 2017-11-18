package emperatriz.deipno

import android.Manifest
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import android.media.MediaPlayer
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.content.DialogInterface
import android.R.string.cancel
import android.hardware.*
import android.support.v7.app.AlertDialog


class MainActivity : AppCompatActivity(), ZXingScannerView.ResultHandler, SensorEventListener {

    var mScannerView: ZXingScannerView? = null
    var imgLupa: ImageView?=null;
    var txtQr: TextView?=null;
    private var mSensorManager: SensorManager? = null
    private var mLight: Sensor? = null
    private var torchOn: Boolean?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.test2);
        mScannerView = findViewById(R.id.scanner) as ZXingScannerView
        mScannerView!!.setBorderAlpha(0f)

        imgLupa = findViewById(R.id.imgLupa) as ImageView
        txtQr = findViewById(R.id.txtQr) as TextView

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Necesitamos usar la cámara para poder encontrar pistas.\nA continuación el sistema te pedirá permiso para usarla.\nAcepta para poder buscar las pistas.")
                    .setPositiveButton("Continuar", DialogInterface.OnClickListener { dialog, id ->
                        ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.CAMERA),12344)

                    })
            builder.create()
            builder.show()

        }

        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        mLight = mSensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT);


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
        //mScannerView!!.resumeCameraPreview(this);

        val mPlayer = MediaPlayer.create(this, R.raw.eureka)
        mPlayer.start()
        imgLupa!!.setImageResource(R.drawable.lupa2)
        txtQr!!.text=p0.toString()
        txtQr!!.visibility = View.VISIBLE
        txtQr!!.animate().alpha(1f).setDuration(3000)
    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this)
        mScannerView!!.startCamera()
        mSensorManager!!.registerListener(this as SensorEventListener, mLight, SensorManager.SENSOR_DELAY_NORMAL)
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
        mSensorManager!!.unregisterListener(this as SensorEventListener);
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val luxTriggerUp = 0.8f
        val luxTriggerDown = 0.3f
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
