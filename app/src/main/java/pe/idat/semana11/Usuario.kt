package pe.idat.semana11

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Usuario {
    @PrimaryKey
    lateinit var usuario: String
    var nombre: String? = null
    var password: String? = null

    constructor(nombre: String, usuario:String,password:String){
        this.nombre=nombre
        this.usuario=usuario
        this.password=password
    }
}