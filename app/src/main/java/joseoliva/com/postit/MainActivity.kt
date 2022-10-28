package joseoliva.com.postit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
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


        //inicializamos el viewmodel con un provider y le pasamos nuestra clase de PostitViewModel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(PostItViewModel::class.java)

        //Llamamos a la lista de postit del viewModel para observar los cambios que haya en la lista
        //lo puedo "observar" porque es un LiveData
        viewModel.listapostit.observe(this, Observer { list ->list?.let{
            //actualizamos la lista
            //postitRVAdapter.UpdateList(it)
        }
        })

        btnadd.setOnClickListener {
            Toast.makeText(this,"Pulsado add",Toast.LENGTH_SHORT).show()
        }


    }
}