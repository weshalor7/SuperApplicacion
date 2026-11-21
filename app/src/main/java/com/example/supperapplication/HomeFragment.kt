package com.example.supperapplication

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private lateinit var btnMercadona: Button
    private lateinit var btnCarrefour: Button
    private lateinit var btnLidl: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_home_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnMercadona = view.findViewById(R.id.btnMercadona)
        btnCarrefour = view.findViewById(R.id.btnCarrefour)
        btnLidl = view.findViewById(R.id.btnLidl)

        seleccionarSupermercado("Carrefour")

        btnMercadona.setOnClickListener { seleccionarSupermercado("Mercadona") }
        btnCarrefour.setOnClickListener { seleccionarSupermercado("Carrefour") }
        btnLidl.setOnClickListener { seleccionarSupermercado("Lidl") }

        val tvProteina = view.findViewById<TextView>(R.id.tvProteina)
        val sbProteina = view.findViewById<SeekBar>(R.id.sbProteina)
        val tvCalorias = view.findViewById<TextView>(R.id.tvCalorias)
        val sbCalorias = view.findViewById<SeekBar>(R.id.sbCalorias)

        tvProteina.text = "Proteïna: ${sbProteina.progress} g"
        tvCalorias.text = "Calories: ${sbCalorias.progress} Kcal"

        sbProteina.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvProteina.text = "Proteïna: $progress g"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        sbCalorias.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvCalorias.text = "Calories: $progress Kcal"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        val productosDePrueba = listOf(
            Producto("Pit de pollastre + Arròs integral", "1,38 €", "35g", "403"),
            Producto("Salmó a la planxa + Patata", "3,20 €", "28g", "480"),
            Producto("Iogurt proteic + Civada", "1,15 €", "20g", "250"),
            Producto("Truita de clares + Tonyina", "1,90 €", "32g", "290")
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvListaProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ProductoAdapter(productosDePrueba)

        val fabAfegir = view.findViewById<FloatingActionButton>(R.id.fabAfegirProducte)
        fabAfegir.setOnClickListener {
            Toast.makeText(requireContext(), "Afegint un nou producte...", Toast.LENGTH_SHORT).show()
            Log.d("MacroSuper", "Event AFEGIR polsat")
        }
    }

    private fun seleccionarSupermercado(supermercado: String) {
        val verdeActivo = ContextCompat.getColor(requireContext(), R.color.verde_macrosuper)
        val tarjetaInactiva = ContextCompat.getColor(requireContext(), R.color.fondo_tarjeta)
        val textoOscuro = ContextCompat.getColor(requireContext(), R.color.fondo_oscuro)
        val textoBlanco = ContextCompat.getColor(requireContext(), R.color.blanco)

        btnMercadona.backgroundTintList = ColorStateList.valueOf(tarjetaInactiva)
        btnMercadona.setTextColor(textoBlanco)

        btnCarrefour.backgroundTintList = ColorStateList.valueOf(tarjetaInactiva)
        btnCarrefour.setTextColor(textoBlanco)

        btnLidl.backgroundTintList = ColorStateList.valueOf(tarjetaInactiva)
        btnLidl.setTextColor(textoBlanco)

        when (supermercado) {
            "Mercadona" -> {
                btnMercadona.backgroundTintList = ColorStateList.valueOf(verdeActivo)
                btnMercadona.setTextColor(textoOscuro)
            }
            "Carrefour" -> {
                btnCarrefour.backgroundTintList = ColorStateList.valueOf(verdeActivo)
                btnCarrefour.setTextColor(textoOscuro)
            }
            "Lidl" -> {
                btnLidl.backgroundTintList = ColorStateList.valueOf(verdeActivo)
                btnLidl.setTextColor(textoOscuro)
            }
        }
    }
}