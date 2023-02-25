package com.syam.pustakata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt_add = findViewById<Button>(R.id.bt_add)
        bt_add.setOnClickListener {
            val it = Intent(this, AddActivity::class.java)
            startActivity(it)
        }

        val bt_profil = findViewById<ImageView>(R.id.bt_akun)
        bt_profil.setOnClickListener {
            val it = Intent(this, ProfilActivity::class.java)
            startActivity(it)
        }
    }
}