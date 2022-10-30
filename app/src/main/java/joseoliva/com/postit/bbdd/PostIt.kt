package joseoliva.com.postit.bbdd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tablaposits")
data class PostIt(
    @ColumnInfo(name = "titulo")
    val titulo: String,
    @ColumnInfo(name = "nota")
    val nota: String,
    @ColumnInfo(name = "fecha")
    val fecha: String,
    @ColumnInfo(name = "color")
    var color: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
