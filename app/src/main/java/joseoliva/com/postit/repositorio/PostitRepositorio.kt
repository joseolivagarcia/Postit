package joseoliva.com.postit.repositorio

import androidx.lifecycle.LiveData
import joseoliva.com.postit.bbdd.PostIt
import joseoliva.com.postit.bbdd.PostItDao

/*
El repositorio es una clase desde la que se decide de donde
obtener los datos. Pueden obtenerse desde una API o desde una
bbdd local como Room.
El repositorio tiene la logica para decidir de donde obtendra los datos.
Esta clase recibe un objeto de tipo DAO
 */
class PostitRepositorio(val postItDao: PostItDao) {
    //aqui recupero todos los postit que haya en la bbdd
    val listaPostit: LiveData<List<PostIt>> = postItDao.getAllPostit()

    //y creo las funciones que usare para cada operacion
    suspend fun insertpostit(postIt: PostIt){
        postItDao.insertpostit(postIt)
    }
    suspend fun deletepostit(postIt: PostIt){
        postItDao.deletepostit(postIt)
    }
    suspend fun updatepostit(postIt: PostIt){
        postItDao.updatepostit(postIt)
    }
}