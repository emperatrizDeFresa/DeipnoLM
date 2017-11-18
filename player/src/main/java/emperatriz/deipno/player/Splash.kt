package emperatriz.deipno.player

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.widget.*

class Splash : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashl)
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY))

        val handler = Handler()
        handler.postDelayed(Runnable {

            if (Sys.getVelada(this)==-1){
                startActivity(Intent(this@Splash, Inicial::class.java))
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
            }else{
                startActivity(Intent(this@Splash, Principal::class.java))
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
            }
            finish()
        }, 1500)


    }



}