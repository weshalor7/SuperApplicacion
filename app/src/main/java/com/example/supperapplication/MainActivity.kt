package com.example.supperapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, HomeFragment())
                .commit()
            supportActionBar?.title = "Inici"
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.menubottom)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, HomeFragment())
                        .commit()
                    supportActionBar?.title = "Inici"
                    Log.i("mainactivity", "Cambiado a HomeFragment")
                    true
                }
                R.id.nav_preferences -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, PreferencesFragment())
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit()
                    supportActionBar?.title = "Preferencies"
                    Log.i("mainactivity", "Cambiado a PreferencesFragment")
                    true
                }
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, ProfileFragment())
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit()
                    supportActionBar?.title = "Perfil"
                    Log.i("mainactivity", "Cambiado a ProfileFragment")
                    true
                }
                else -> false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            return true
        }
        return super.onSupportNavigateUp()
    }
}