package pe.idat.semana11

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Usuario::class],
    version = 1
)
abstract class BDUsuario : RoomDatabase(){
    abstract  fun daoUsuario():DaoUsuario
}