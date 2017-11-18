package emperatriz.deipno.player

import android.R.attr.*
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.graphics.Typeface
import android.view.WindowManager
import android.util.TypedValue
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


class Inicial : AppCompatActivity(), View.OnFocusChangeListener {
    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus){
            val scrInicial = findViewById(R.id.scrInicial) as ScrollView
            scrInicial.visibility = View.GONE
            val txtClave = findViewById(R.id.txtCode) as EditText
            val paddingDp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, getResources().getDisplayMetrics()).toInt()
            val padding2Dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60f, getResources().getDisplayMetrics()).toInt()
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, padding2Dp)
            lp.setMargins(0, paddingDp, 0, 0)
            txtClave.setLayoutParams(lp)
        }
    }

    var btnInicial1:Button?=null

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (true||hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                            .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                            .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                            .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                            .or(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                        .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                        .or(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY))

        val externalFont = Sys.getFont(this@Inicial)

        val txtInicial = findViewById(R.id.txtInicial) as TextView
        txtInicial.typeface=externalFont


        val txtClave = findViewById(R.id.txtCode) as EditText
        txtClave.typeface=externalFont
        txtClave.onFocusChangeListener = this
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        txtClave.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // you can check for enter key here
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (Sys.checkClave(s.toString(),this@Inicial)){
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0)
                    getWindow().getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                                    .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                                    .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                                    .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                                    .or(View.SYSTEM_UI_FLAG_IMMERSIVE))
                    //Toast.makeText(this@Inicial,"Velada "+Sys.getVelada(this@Inicial)+" Personaje "+Sys.getPersonaje(this@Inicial),Toast.LENGTH_SHORT).show()
                    Sys.setPistaState(Sys.getPersonaje(this@Inicial),Sys.PISTA_DESCUBIERTA,this@Inicial)
                    Sys.addConfirmations(Sys.getPersonaje(this@Inicial),this@Inicial)
                    startActivity(Intent(this@Inicial, Principal::class.java))
                    overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
                    finish()
                }
            }
        })


    }



}
