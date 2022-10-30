package joseoliva.com.postit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import joseoliva.com.postit.adapter.PostitAdapter
import joseoliva.com.postit.bbdd.PostIt
import joseoliva.com.postit.databinding.ActivityMainBinding
import joseoliva.com.postit.viewmodel.PostItViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    //creo las var para iniciar los distintos elementos
    lateinit var viewModel: PostItViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var btnadd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.rvpostit
        btnadd = binding.fab

        //creamos el manager del recyclerview
        recyclerView.layoutManager = GridLayoutManager(this,2) //si usamos LinearLayoutManager saldran en fila de 1
        //inicializamos el adapter y le pasamos como parametros el contexto y las interface que necesita
        val postitRVAdapter = PostitAdapter(onClickListener = {postIt -> onItemSelected(postIt) }, onClickDelete = {posit -> onDeleteItem(posit)})
        //ponemos el adapter que acabamos de referenciar al recyclerview
        recyclerView.adapter = postitRVAdapter


        //inicializamos el viewmodel con un provider y le pasamos nuestra clase de PostitViewModel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(PostItViewModel::class.java)

        //Llamamos a la lista de postit del viewModel para observar los cambios que haya en la lista
        //lo puedo "observar" porque es un LiveData
        viewModel.listapostit.observe(this, Observer { list ->list?.let{
            //actualizamos la lista
            postitRVAdapter.updateList(it)
        }
        })

        btnadd.setOnClickListener {
            //meto manualmente alguna nota para probar
            val posit1 = PostIt(0,"Festivos","tengo 2 festivos","hoy","naranja")
            viewModel.insertPosit(posit1)
            val posit2 = PostIt(0,"Partido","Sabado a las 12","mañana","rojo")
            viewModel.insertPosit(posit2)
        }

    }

    private fun onItemSelected(postIt: PostIt){
        Toast.makeText(this, postIt.titulo, Toast.LENGTH_SHORT).show()
    }

    private fun onDeleteItem(postIt: PostIt){
        viewModel.deletePostit(postIt)
    }
}