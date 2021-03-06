package com.example.master

import android.app.AlarmManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.app.AlarmManager.RTC_WAKEUP
import android.app.PendingIntent
import android.app.PendingIntent.getBroadcast
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.os.Environment
import android.os.StrictMode
import android.widget.Toast
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import android.os.StrictMode.setVmPolicy




class Utilidades : AppCompatActivity(), View.OnClickListener {

    var imprimibles: Button?=null;
    var temporizador: Button?=null;
    var cast: Button?=null;
    var volver: Button?=null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utilidades)

        imprimibles = findViewById<Button>(R.id.p51)
        imprimibles!!.setOnClickListener(this)
        temporizador = findViewById<Button>(R.id.p52)
        temporizador!!.setOnClickListener(this)
        cast = findViewById<Button>(R.id.p53)
        cast!!.setOnClickListener(this)
        volver = findViewById<Button>(R.id.volver)
        volver!!.setOnClickListener(this)

        val inicio = findViewById<Button>(R.id.inicio)
        inicio.setOnClickListener( View.OnClickListener {
            finish()
            startActivity(Intent(this@Utilidades, Principal::class.java))
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out)
        })

    }

    @Throws(IOException::class)
    private fun copyPdfFile(i: InputStream, o: OutputStream) {
        val buffer = ByteArray(1024)
        var read = i.read(buffer)
        while (read  != -1) {
            o.write(buffer, 0, read)
            read = i.read(buffer)
        }
    }

    override fun onClick(v: View?) {
        if (v == imprimibles){
            startActivity(Intent(this@Utilidades, PDFReader::class.java))
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        } else  if (v== temporizador){
            val w = System.currentTimeMillis() + 85*60000L
            val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(this, MyReceiver::class.java)
            intent.putExtra(getString(R.string.alarmaTitulo), getString(R.string.alarmaTexto))
            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
            am.set(AlarmManager.RTC_WAKEUP, w, pendingIntent)

            Toast.makeText(this, R.string.alarma, Toast.LENGTH_SHORT).show()

        }else  if (v==volver){
            finish()
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out)
        }else if (v==cast){
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=SSdejZVfcpc")))
        }
    }



}