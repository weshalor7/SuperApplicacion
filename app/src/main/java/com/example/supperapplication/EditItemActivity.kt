package com.example.supperapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item2) // Revisa si tu XML se llama activity_edit_item o activity_edit_item2

        val etName = findViewById<EditText>(R.id.etEditName)
        val etPrice = findViewById<EditText>(R.id.etEditPrice)
        val etProtein = findViewById<EditText>(R.id.etEditProtein)
        val etCalories = findViewById<EditText>(R.id.etEditCalories)
        val btnSave = findViewById<Button>(R.id.btnSaveItem)
        val btnCancel = findViewById<Button>(R.id.btnCancelEdit)

        // 1. Recibir datos actuales del producto seleccionado
        val position = intent.getIntExtra("EXTRA_POSITION", -1)
        etName.setText(intent.getStringExtra("EXTRA_NOMBRE") ?: "")
        etPrice.setText(intent.getStringExtra("EXTRA_PRECIO")?.replace(" €", "") ?: "")
        etProtein.setText(intent.getStringExtra("EXTRA_PROTEINAS")?.replace("g", "") ?: "")
        etCalories.setText(intent.getStringExtra("EXTRA_CALORIAS") ?: "")

        // 2. Guardar cambios y devolverlos a HomeFragment
        btnSave.setOnClickListener {
            val precioFormateado = "${etPrice.text.toString().trim()} €"
            val protFormateada = "${etProtein.text.toString().trim()}g"

            val resultIntent = Intent().apply {
                putExtra("EXTRA_POSITION", position)
                putExtra("EXTRA_NOMBRE", etName.text.toString().trim())
                putExtra("EXTRA_PRECIO", precioFormateado)
                putExtra("EXTRA_PROTEINAS", protFormateada)
                putExtra("EXTRA_CALORIAS", etCalories.text.toString().trim())
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        // 3. Cancelar y volver sin cambios
        btnCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}