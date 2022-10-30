package joseoliva.com.postit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import joseoliva.com.postit.bbdd.PostIt
import joseoliva.com.postit.bbdd.PostItDataBase
import joseoliva.com.postit.repositorio.PostitRepositorio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
El viewModel es la clase que se ocupa de proveer de datos a la UI
Es una especie de intermediaria entre el repositorio y la UI.
 */
class PostItViewModel(application: Application): AndroidViewModel(application) {
    /*
    creamos una var donde guardaremos todas las notas (una lista)
    y una var para referenciar nuestro repositorio
    Daran error hasta que las inicialicemos
     */
    val listapostit: LiveData<List<PostIt>>
    val repositorio: PostitRepositorio
    init{
        /* el dao lo obtengo desde la clase PostItDataBase llamando a la fun getDatabase
            y pasandola el contexto que estoy cogiendo de esta misma clase
            y llamando a la fun getPositDao de la misma clase NoteDataBase
         */
        val dao = PostItDataBase.getDatabase(application).getPostitDao()
        //inicializo el repo pasandole el dao que acabo de obtener
        repositorio = PostitRepositorio(dao)
        //y obtengo todas las notas en la var que creé arriba
        listapostit = repositorio.listaPostit
    }
    /*
     Me creo las funciones para insertat,borrar o editar postits. LLamare a las funciones que
     hay en el repositorio.
     Lo hago con viewModelScope para no hacerlo en el hilo ppal y no bloquear la app
     */
    fun addPosit (postIt: PostIt) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.insertpostit(postIt)
    }
    fun deletePostit(postIt: PostIt) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.deletepostit(postIt)
    }
    fun updatePostit(postIt: PostIt) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.updatepostit(postIt)
    }
}