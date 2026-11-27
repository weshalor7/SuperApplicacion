package com.example.supperapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(
    private val listaProductos: MutableList<Producto>,
    private val onEditarClick: (Producto, Int) -> Unit
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

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
        val context = holder.itemView.context

        holder.tvNombre.text = producto.nombre
        holder.tvPrecio.text = producto.precio
        holder.tvProteinas.text = context.getString(R.string.formato_proteina, producto.proteinas)
        holder.tvCalorias.text = context.getString(R.string.formato_calorias, producto.calorias)

        // Etiquetado accesible dinámico para TalkBack
        holder.btnEditar.contentDescription = context.getString(R.string.desc_editar_producto, producto.nombre)
        holder.btnBorrar.contentDescription = context.getString(R.string.desc_borrar_producto, producto.nombre)

        // Evento MODIFICAR: delega al Fragment para abrir EditItemActivity con ActivityResultLauncher
        holder.btnEditar.setOnClickListener {
            val posActual = holder.adapterPosition
            if (posActual != RecyclerView.NO_POSITION) {
                onEditarClick(listaProductos[posActual], posActual)
            }
        }

        // Evento ELIMINAR: borra de verdad de la lista mutable
        holder.btnBorrar.setOnClickListener {
            val posActual = holder.adapterPosition
            if (posActual != RecyclerView.NO_POSITION) {
                val nombreBorrado = listaProductos[posActual].nombre
                eliminarProducto(posActual)
                Toast.makeText(context, context.getString(R.string.msg_producto_eliminado, nombreBorrado), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = listaProductos.size

    fun eliminarProducto(position: Int) {
        listaProductos.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listaProductos.size)
    }

    fun actualizarProducto(position: Int, productoActualizado: Producto) {
        if (position in listaProductos.indices) {
            listaProductos[position] = productoActualizado
            notifyItemChanged(position)
        }
    }

    fun agregarProducto(nuevoProducto: Producto) {
        listaProductos.add(nuevoProducto)
        notifyItemInserted(listaProductos.size - 1)
    }
}