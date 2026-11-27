/*package com.example.supperapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_list)

        // 1. Lista de datos de prueba
        val productosDePrueba = listOf(
            Producto("Pit de pollastre + Arròs integral", "1,38 €", "35g", "403"),
            Producto("Salmó a la planxa + Patata", "3,20 €", "28g", "480"),
            Producto("Iogurt proteic + Civada", "1,15 €", "20g", "250"),
            Producto("Truita de clares + Tonyina", "1,90 €", "32g", "290")
        )

        // 2. Configurar el RecyclerView con su Adapter
        val recyclerView = findViewById<RecyclerView>(R.id.rvListaProductos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductoAdapter(productosDePrueba)

        // 3. Evento AFEGIR (Botón flotante +)
        val fabAfegir = findViewById<FloatingActionButton>(R.id.fabAfegirProducte)
        fabAfegir.setOnClickListener {
            Toast.makeText(this, "Afegint un nou producte...", Toast.LENGTH_SHORT).show()
            Log.d("MacroSuper", "Event AFEGIR polsat")
        }
    }
}*/