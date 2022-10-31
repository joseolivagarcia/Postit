package joseoliva.com.postit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
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

        //para usar el buscador
        binding.buscador.addTextChangedListener {
            val listafiltrada = viewModel.listapostit.value!!.filter{
                    postIt -> postIt.titulo.lowercase().contains(it.toString().lowercase())
            }
            postitRVAdapter.listaNotasFiltradas(listafiltrada)
        }


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
            //si pulso el boton add "+" voy a la actividad de crear un postit
            val intent = Intent(this,EditAddActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }

    private fun onItemSelected(postIt: PostIt){
        /*
        Si pulso sobre un postit, voy a la actividad de editar/crear posit
        En este caso al pulsar sobre uno existente se entiende que lo vamos a editar
        asi que le paso los datos que tiene para mostrarlos en la otra activity
         */
        val intent = Intent(this,EditAddActivity::class.java)
        intent.putExtra("tipoposit", "Editar")
        intent.putExtra("id", postIt.id)
        intent.putExtra("titulo", postIt.titulo)
        intent.putExtra("posit", postIt.nota)
        intent.putExtra("fecha", postIt.fecha)
        intent.putExtra("color", postIt.color)
        startActivity(intent)

    }

    private fun onDeleteItem(postIt: PostIt){
        viewModel.deletePostit(postIt)
    }

    @Override
    override fun onBackPressed() {

    }
}