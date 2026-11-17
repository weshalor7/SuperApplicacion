package com.example.supperapplication

import android.os.Bundle
import android.os.Looper
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        // 1. Animación de logotipo (Aparece y crece un poco)
        val logo = findViewById<ImageView>(R.id.imgLogoSplash)
        logo.animate().apply {
            duration = 1500
            alpha(1f)
            scaleX(1.2f)
            scaleY(1.2f)
        }.start()

        // 2. Temporizador para cambiar de pantalla a los 3 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            //finish() para que el usuario no pueda volver a esta pantalla al darle atras.
            finish()
        }, 3000)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}