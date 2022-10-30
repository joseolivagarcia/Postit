package joseoliva.com.postit.adapter

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.postit.R
import joseoliva.com.postit.bbdd.PostIt

class PostitViewHolder(view: View): RecyclerView.ViewHolder(view) {

    var titulo = view.findViewById<TextView>(R.id.TVpostit)
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

        positfondo.setBackgroundColor(Color.parseColor(color))
        

        itemView.setOnClickListener {
            onClickListener(positmodel)
        }

        btndelete.setOnClickListener {
            onClickDelete(positmodel)
        }
    }


}