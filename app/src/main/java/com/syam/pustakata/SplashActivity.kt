package com.syam.pustakata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // Ketika timer berakhir, jalankan MainActivity
            startActivity(Intent(this, MainActivity::class.java))

            // Tutup Splash Activity agar tidak dapat diakses lagi
            finish()
        }, 3000)
    }
}