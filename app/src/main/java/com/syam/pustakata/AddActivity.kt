package com.syam.pustakata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val bt_profil = findViewById<Button>(R.id.bt_batal)
        bt_profil.setOnClickListener {
            finish()
        }
    }
}