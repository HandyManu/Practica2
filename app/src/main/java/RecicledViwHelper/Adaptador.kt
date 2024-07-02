package RecicledViwHelper

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import manuel.ortega.prueba2.ActivityDetalleTiket
import manuel.ortega.prueba2.R
import modelo.claseConexion
import modelo.dataClassTikets


class Adaptador(var Datos: List<dataClassTikets>) : RecyclerView.Adapter<ViewHolder>() {

    fun actualizarLista(nuevaLista: List<dataClassTikets>) {
        Datos = nuevaLista
        notifyDataSetChanged()
    }

    fun actualizarListaDespuesDeActualizarDatos(uuid: String, nuevoEstado: String) {
        val index = Datos.indexOfFirst { it.uuidNumero == uuid }
        Datos[index].estado = nuevoEstado
        notifyItemChanged(index)
    }

    fun eliminarRegistro(estado: String, position: Int) {
        val listaDatos = Datos.toMutableList()
        listaDatos.removeAt(position)

        GlobalScope.launch(Dispatchers.IO) {
            val objConexion = claseConexion().cadenaConexion()
            val deleteProducto = objConexion?.prepareStatement("DELETE FROM tb_tikets WHERE uuidNumero = ?")!!
            deleteProducto.setString(1, estado)
            deleteProducto.executeUpdate()
            val commit = objConexion.prepareStatement("COMMIT")!!
            commit.executeUpdate()
        }
        Datos = listaDatos.toList()
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    fun actualizarProducto(estado: String, uuid: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val objConexion = claseConexion().cadenaConexion()
            val updateProducto = objConexion?.prepareStatement("UPDATE tb_tikets SET estado = ? WHERE uuidNumero = ?")!!
            updateProducto.setString(1, estado)
            updateProducto.setString(2, uuid)
            updateProducto.executeUpdate()
            val commit = objConexion.prepareStatement("COMMIT")
            commit.executeUpdate()
            withContext(Dispatchers.Main) {
                actualizarListaDespuesDeActualizarDatos(uuid, estado)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Datos[position]
        holder.textView.text = item.estado

        holder.imgBorrar.setOnClickListener {
            val context = holder.itemView.context
            val builder = AlertDialog.Builder(context)
            builder.setTitle("¿Estás seguro?")
            builder.setMessage("¿Deseas en verdad eliminar el registro?")
            builder.setPositiveButton("Sí") { dialog, which ->
                eliminarRegistro(item.estado, position)
            }
            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }

        holder.imgEditar.setOnClickListener {
            val context = holder.itemView.context
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Editar estado")
            val cuadritoNuevoEstado = EditText(context)
            cuadritoNuevoEstado.setHint(item.estado)
            builder.setView(cuadritoNuevoEstado)
            builder.setPositiveButton("Actualizar") { dialog, which ->
                actualizarProducto(cuadritoNuevoEstado.text.toString(), item.uuidNumero)
            }
            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val pantallaDetalles = Intent(context, ActivityDetalleTiket::class.java)
            pantallaDetalles.putExtra("uuidNumero", item.uuidNumero)
            pantallaDetalles.putExtra("estado", item.estado)
            context.startActivity(pantallaDetalles)
        }
    }

    override fun getItemCount() = Datos.size
}