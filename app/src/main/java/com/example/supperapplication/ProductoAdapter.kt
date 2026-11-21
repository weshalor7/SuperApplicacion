package com.example.supperapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(private val listaProductos: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

        class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvNombre: TextView = view.findViewById(R.id.tvNombreProducto)
            val tvPrecio: TextView = view.findViewById(R.id.tvPrecioProducto)
            val tvProteinas: TextView = view.findViewById(R.id.tvProteinasProducto)
            val tvCalorias: TextView = view.findViewById(R.id.tvCaloriasProducto)
            val btnEditar: Button = view.findViewById(R.id.btnEditarProducto)
            val btnBorrar: Button = view.findViewById(R.id.btnBorrarProducto)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }
    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = listaProductos[position]

        holder.tvNombre.text = producto.nombre
        holder.tvPrecio.text = producto.precio
        holder.tvProteinas.text = "Prot: ${producto.proteinas}"
        holder.tvCalorias.text = "Kcal: ${producto.calorias}"

        // Evento MODIFICAR
        holder.btnEditar.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Modificant: ${producto.nombre}", Toast.LENGTH_SHORT).show()
            Log.d("MacroSuper", "Event MODIFICAR en: ${producto.nombre}")
        }

        // Evento ELIMINAR
        holder.btnBorrar.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Eliminant: ${producto.nombre}", Toast.LENGTH_SHORT).show()
            Log.d("MacroSuper", "Event ELIMINAR en: ${producto.nombre}")
        }
    }
    override fun getItemCount(): Int = listaProductos.size
    }