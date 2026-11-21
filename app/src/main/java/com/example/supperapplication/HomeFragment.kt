package com.example.supperapplication

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

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

        btnMercadona.setOnClickListener {
            seleccionarSupermercado("Mercadona")
        }

        btnCarrefour.setOnClickListener {
            seleccionarSupermercado("Carrefour")
        }

        btnLidl.setOnClickListener {
            seleccionarSupermercado("Lidl")
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