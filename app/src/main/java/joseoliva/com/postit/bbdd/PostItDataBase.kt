package joseoliva.com.postit.bbdd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(
    entities = arrayOf(PostIt::class),
    version = 1
)
abstract class PostItDataBase: RoomDatabase() {

    abstract fun getPostitDao(): PostItDao
    /*
    Metemos el companion object para prevenir que se abran
    multiples instancias de la bbdd al mismo tiempo
     */
    companion object{
        @Volatile
        private var INSTANCE: PostItDataBase? = null
        fun getDatabase(context: Context): PostItDataBase{
            //si la instancia no es nula la retorna
            //si es nula, crea la bbdd
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostItDataBase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                //devolvemos la instance
                instance
            }
        }
    }
}