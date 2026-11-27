package com.example.supperapplication

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private lateinit var btnMercadona: Button
    private lateinit var btnCarrefour: Button
    private lateinit var btnLidl: Button
    private lateinit var productoAdapter: ProductoAdapter
    private lateinit var recyclerView: RecyclerView

    // Launcher para recibir los datos modificados desde EditItemActivity
    private val editItemLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val data = result.data!!
            val position = data.getIntExtra("EXTRA_POSITION", -1)
            val nombre = data.getStringExtra("EXTRA_NOMBRE") ?: ""
            val precio = data.getStringExtra("EXTRA_PRECIO") ?: ""
            val proteinas = data.getStringExtra("EXTRA_PROTEINAS") ?: ""
            val calorias = data.getStringExtra("EXTRA_CALORIAS") ?: ""

            if (position != -1) {
                val productoActualizado = Producto(nombre, precio, proteinas, calorias)
                productoAdapter.actualizarProducto(position, productoActualizado)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_producto_actualizado, nombre),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_home_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Botones de supermercado
        btnMercadona = view.findViewById(R.id.btnMercadona)
        btnCarrefour = view.findViewById(R.id.btnCarrefour)
        btnLidl = view.findViewById(R.id.btnLidl)

        seleccionarSupermercado("Carrefour")

        btnMercadona.setOnClickListener { seleccionarSupermercado("Mercadona") }
        btnCarrefour.setOnClickListener { seleccionarSupermercado("Carrefour") }
        btnLidl.setOnClickListener { seleccionarSupermercado("Lidl") }

        // 2. Sliders dinámicos
        val tvProteina = view.findViewById<TextView>(R.id.tvProteina)
        val sbProteina = view.findViewById<SeekBar>(R.id.sbProteina)
        val tvCalorias = view.findViewById<TextView>(R.id.tvCalorias)
        val sbCalorias = view.findViewById<SeekBar>(R.id.sbCalorias)

        tvProteina.text = getString(R.string.formato_slider_proteina, sbProteina.progress)
        tvCalorias.text = getString(R.string.formato_slider_calorias, sbCalorias.progress)

        sbProteina.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvProteina.text = getString(R.string.formato_slider_proteina, progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        sbCalorias.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvCalorias.text = getString(R.string.formato_slider_calorias, progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // 3. Lista MUTABLE de datos de prueba y RecyclerView conectado a EditItemActivity
        val productosDePrueba = mutableListOf(
            Producto("Pit de pollastre + Arròs integral", "1,38 €", "35g", "403"),
            Producto("Salmó a la planxa + Patata", "3,20 €", "28g", "480"),
            Producto("Iogurt proteic + Civada", "1,15 €", "20g", "250"),
            Producto("Truita de clares + Tonyina", "1,90 €", "32g", "290")
        )

        recyclerView = view.findViewById(R.id.rvListaProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        productoAdapter = ProductoAdapter(productosDePrueba) { producto, position ->
            val intent = Intent(requireContext(), EditItemActivity::class.java).apply {
                putExtra("EXTRA_POSITION", position)
                putExtra("EXTRA_NOMBRE", producto.nombre)
                putExtra("EXTRA_PRECIO", producto.precio)
                putExtra("EXTRA_PROTEINAS", producto.proteinas)
                putExtra("EXTRA_CALORIAS", producto.calorias)
            }
            editItemLauncher.launch(intent)
        }
        recyclerView.adapter = productoAdapter

        // 4. Evento AFEGIR en el botón flotante +
        val fabAfegir = view.findViewById<FloatingActionButton>(R.id.fabAfegirProducte)
        fabAfegir.setOnClickListener {
            val nuevoProducto = Producto("Nou producte proteic", "2,50 €", "30g", "350")
            productoAdapter.agregarProducto(nuevoProducto)
            recyclerView.scrollToPosition(productoAdapter.itemCount - 1)
            Toast.makeText(
                requireContext(),
                getString(R.string.msg_producto_afegit),
                Toast.LENGTH_SHORT
            ).show()
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