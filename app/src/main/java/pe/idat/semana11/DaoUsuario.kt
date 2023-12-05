package pe.idat.semana11

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoUsuario{

    @Query("SELECT * FROM usuario")
    suspend fun getUsuarios(): List<Usuario>

    @Query("SELECT * FROM usuario WHERE usuario=:usuario")
    suspend fun getUsuario(usuario:String)

    @Insert
    suspend fun insertUsuario(usuario:Usuario)

    @Query("UPDATE usuario SET nombre=:nombre, password=:password WHERE usuario=:usuario")
    suspend fun updateUsuario(usuario:String,nombre:String,password:String):Usuario

    @Query("DELETE FROM usuario WHERE usuario = usuario") //deberia usarse =:
    suspend fun deleteUsuario(usuario: String)

}