package joseoliva.com.postit.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.postit.R
import joseoliva.com.postit.bbdd.PostIt

class PostitAdapter(
    private val onClickListener:(PostIt) -> Unit,
    private val onClickDelete:(PostIt) -> Unit
): RecyclerView.Adapter<PostitViewHolder>() {

    //creo una var de lista donde guardare todos los postit
    private val allPostit = ArrayList<PostIt>()
    lateinit var color: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostitViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PostitViewHolder(layoutInflater.inflate(R.layout.postit_rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: PostitViewHolder, position: Int) {
        val item = allPostit[position]
        if(item.color.equals("verde")){
            color = "#8BC34A"
        }
        if(item.color.equals("azul")){
            color = "#00BCD4"
        }
        if(item.color.equals("naranja")){
            color = "#FF9800"
        }
        if(item.color.equals("amarillo")){
            color = "#FFEB3B"
        }
        holder.render(item,onClickListener,onClickDelete,color)

    }

    override fun getItemCount(): Int {
        return allPostit.size
    }

    fun updateList(newList: List<PostIt>){
        allPostit.clear()
        allPostit.addAll(newList)
        notifyDataSetChanged()
    }

}