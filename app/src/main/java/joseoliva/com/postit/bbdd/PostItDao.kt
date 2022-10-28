package joseoliva.com.postit.bbdd

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostItDao {
    //query para obtener todas los posit ordenados por id
    @Query("Select * from tablaposits Order By id Asc")
    fun getAllPostit(): LiveData<List<PostIt>>

    //operaciones de insertar,borrar y actualizar
    @Insert
    suspend fun insertpostit(postIt: PostIt)
    @Delete
    suspend fun deletepostit(postIt: PostIt)
    @Update
    suspend fun updatepostit(postIt: PostIt)
}