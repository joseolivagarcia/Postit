package joseoliva.com.postit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import joseoliva.com.postit.bbdd.PostIt
import joseoliva.com.postit.databinding.ActivityEditAddBinding
import joseoliva.com.postit.viewmodel.PostItViewModel
import java.text.SimpleDateFormat
import java.util.*

class EditAddActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditAddBinding

    lateinit var positTitleET: EditText
    lateinit var positET: EditText
    lateinit var saveBtn: Button
    var id: Int = -1 //le doy este valor porque luego usare el real
    lateinit var color: String //le doy este valor pero luego uso el que reciba
    //creo una var para el viewmodel y una entera para el id
    lateinit var viewmodel: PostItViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inicializo el viewmodel
        viewmodel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(PostItViewModel::class.java)
        //y el resto de variables
        positTitleET = binding.ETpositName
        positET = binding.ETpositDescription
        saveBtn = binding.idBtn

        /*
        obtengo los datos que recibo por el intent, primero compruebo si recibo el dato que me dice
        "edit" para saber si es un posit a editar o voy a crear uno nuevo.
        De ser a editar necesito pasarle los datos que trae, de ser uno nuevo capturare lo que escriba el
        usuario.
        El boton tendra un texto u otro dependiendo si editamos o creamos
         */
        val tipopostit = intent.getStringExtra("tipoposit")
        if(tipopostit.equals("Editar")){
            val titulo = intent.getStringExtra("titulo")
            val posit = intent.getStringExtra("posit")

            positTitleET.setText(titulo)
            positET.setText(posit)
            saveBtn.text = "Actualizar PostIt"

        }else{
            saveBtn.text = "Guardar PostIt"
        }

        /*
        Ahora damos funcionalidad al boton y segun estemos editando o creando
        un posit pues hacemos una u otra cosa
         */
        saveBtn.setOnClickListener {
            //obtengo lo que escriba el usuario
            val titulo = positTitleET.text.toString()
            val posit = positET.text.toString()
            color = intent.getStringExtra("color").toString()
            id = intent.getIntExtra("id",-1)
            if(tipopostit.equals("Editar")){
                if(titulo.isNotEmpty() && posit.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentdatetime: String = sdf.format(Date())
                    val updateposit = PostIt(titulo,posit,currentdatetime,color)
                    updateposit.id = id
                    viewmodel.updatePostit(updateposit)
                }
            }else{
                /*
                val newpos = PostIt("Hola","prueba de la app manual","hoy","verde")
                viewmodel.addPosit(newpos)

                 */

                if(titulo.isNotEmpty() && posit.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentdatetime: String = sdf.format(Date())
                    val newposit = PostIt(titulo,posit,currentdatetime,color)
                    viewmodel.addPosit(newposit)
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
    override fun onBackPressed() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}