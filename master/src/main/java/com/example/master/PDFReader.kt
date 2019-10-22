package com.example.master

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.github.barteksc.pdfviewer.PDFView

/**
 * Created by ramon on 11/09/19.
 */
class PDFReader : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfreader)

        val pdfView = findViewById<PDFView>(R.id.pdfv)
        pdfView.fromAsset("ObjetosImprimibles.pdf").load()
    }
}