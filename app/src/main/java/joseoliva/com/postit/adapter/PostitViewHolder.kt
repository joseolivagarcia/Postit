package joseoliva.com.postit.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.postit.R
import joseoliva.com.postit.bbdd.PostIt

class PostitViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val titulo = view.findViewById<TextView>(R.id.TVpostit)
    val fecha = view.findViewById<TextView>(R.id.TVDate)
    val btndelete = view.findViewById<ImageView>(R.id.IVDelete)

    fun render(
        positmodel: PostIt,
        onClickListener: (PostIt) -> Unit,
        onClickDelete: (PostIt) -> Unit
    ){
        titulo.text = positmodel.titulo
        fecha.text = positmodel.fecha

        itemView.setOnClickListener {
            onClickListener(positmodel)
        }

        btndelete.setOnClickListener {
            onClickDelete(positmodel)
        }
    }


}