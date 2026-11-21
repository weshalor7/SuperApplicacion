package com.example.supperapplication

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.main_drawerlayout)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener { item ->
            navigationItemSelectedListener(item)
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, HomeFragment())
                .commit()
            supportActionBar?.title = "Inicio"
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.menubottom)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, HomeFragment())
                        .commit()
                    supportActionBar?.title = "Inicio"
                    toggle.setDrawerIndicatorEnabled(true)
                    Log.i("mainactivity", "Cambiado a HomeFragment")
                    true
                }
                R.id.nav_preferences -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, PreferencesFragment())
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit()
                    supportActionBar?.title = "Preferencias"
                    toggle.setDrawerIndicatorEnabled(false)
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
                    toggle.setDrawerIndicatorEnabled(false)
                    Log.i("mainactivity", "Cambiado a ProfileFragment")
                    true
                }
                else -> false
            }
        }
    }

    private fun navigationItemSelectedListener(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, HomeFragment())
                    .commit()
                supportActionBar?.title = "Inicio"
            }
            R.id.nav_preferences -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, PreferencesFragment())
                    .addToBackStack(null)
                    .commit()
                supportActionBar?.title = "Preferencias"
            }
            R.id.nav_profile -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, ProfileFragment())
                    .addToBackStack(null)
                    .commit()
                supportActionBar?.title = "Perfil"
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            toggle.setDrawerIndicatorEnabled(true)
            return true
        }
        return super.onSupportNavigateUp()
    }
}