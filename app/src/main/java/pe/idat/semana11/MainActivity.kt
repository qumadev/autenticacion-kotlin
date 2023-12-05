package pe.idat.semana11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val room = Room.databaseBuilder(this,BDUsuario::class.java,"usuarios").build()

        lifecycleScope.launch {
            //agregar
            room.daoUsuario().insertUsuario(Usuario("fernando","fer","fer"))
            //listar
            var usuarios = room.daoUsuario().getUsuarios()
            for (item in usuarios) {
                Log.d("crear usuario", "${item.usuario} ${item.nombre} ${item.password}")
            }
            //modificar
            room.daoUsuario().updateUsuario("adrian", "adr", "adr")

            usuarios = room.daoUsuario().getUsuarios()
            for (item in usuarios) {
                Log.d("usuario modificado", "${item.usuario} ${item.nombre} ${item.password}")
            }
        }
    }
}