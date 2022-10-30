package joseoliva.com.postit.adapter

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostitViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PostitViewHolder(layoutInflater.inflate(R.layout.postit_rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: PostitViewHolder, position: Int) {
        val item = allPostit[position]
        holder.render(item,onClickListener,onClickDelete)
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