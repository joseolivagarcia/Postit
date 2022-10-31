package joseoliva.com.postit.adapter

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.postit.R
import joseoliva.com.postit.bbdd.PostIt

class PostitViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val titulo = view.findViewById<TextView>(R.id.TVpostit)
    val fecha = view.findViewById<TextView>(R.id.TVDate)
    val btndelete = view.findViewById<ImageView>(R.id.IVDelete)
    val positfondo = view.findViewById<CardView>(R.id.cardview)

    fun render(
        positmodel: PostIt,
        onClickListener: (PostIt) -> Unit,
        onClickDelete: (PostIt) -> Unit,
        color: String
    ){
        titulo.text = positmodel.titulo
        fecha.text = "Actualizado: " + positmodel.fecha
        //cambio la fuente para unos colores u otros y el color del texto
        if(color.equals("#8BC34A") || color.equals("#00BCD4")){ //para color verde o azul
            titulo.typeface = ResourcesCompat.getFont(titulo.context,R.font.hand_normal)
            titulo.setTextColor(Color.parseColor("#FFFFFFFF"))
        }else{
            titulo.typeface = ResourcesCompat.getFont(titulo.context,R.font.dancing_normal)
            titulo.setTextColor(Color.parseColor("#FF000000"))
        }
        positfondo.setBackgroundColor(Color.parseColor(color))


        itemView.setOnClickListener {
            onClickListener(positmodel)
        }

        btndelete.setOnClickListener {
            onClickDelete(positmodel)
        }
    }


}