package RecicledViwHelper

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import manuel.ortega.prueba2.R

class ViewHolder (view: View):RecyclerView.ViewHolder(view) {

    val textView:TextView=view.findViewById(R.id.txt_tiketCard)
    val imgEditar: ImageView =view.findViewById(R.id.img_editar)
    val imgBorrar: ImageView =view.findViewById(R.id.img_borrar)
}