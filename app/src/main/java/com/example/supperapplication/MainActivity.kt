package com.example.supperapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        findViewById< android.widget.Button >(R.id.btnOnboarding).setOnClickListener {
            startActivity(android.content.Intent(this, OnboardingActivity::class.java))
        }
        findViewById< android.widget.Button >(R.id.btnLogin).setOnClickListener {
            startActivity(android.content.Intent(this, LoginActivity::class.java))
        }
        findViewById< android.widget.Button >(R.id.btnRegister).setOnClickListener {
            startActivity(android.content.Intent(this, RegisterActivity::class.java))
        }
        findViewById< android.widget.Button >(R.id.btnHomeList).setOnClickListener {
            startActivity(android.content.Intent(this, HomeListActivity::class.java))
        }
        findViewById< android.widget.Button >(R.id.btnEditItem).setOnClickListener {
            startActivity(android.content.Intent(this, EditItemActivity::class.java))
        }
        findViewById< android.widget.Button >(R.id.btnPreferences).setOnClickListener {
            startActivity(android.content.Intent(this, PreferencesActivity::class.java))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}