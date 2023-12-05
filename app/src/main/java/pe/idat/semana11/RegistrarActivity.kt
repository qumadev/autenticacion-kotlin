package pe.idat.semana11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import pe.idat.semana11.databinding.ActivityRegistrarBinding

class RegistrarActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistrarBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegistrar.setOnClickListener{
            var usuario:String  = binding.etCorreo.text.toString()
            var clave : String = binding.etClave.text.toString()

            if(usuario.isNullOrEmpty() || clave.isNullOrEmpty()){
                Toast.makeText(this,"Debes completar todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                binding.pbBar.visibility = View.VISIBLE
                firebaseAuth.createUserWithEmailAndPassword(usuario,clave)
                    .addOnCompleteListener(this){
                            task->
                        if(task.isSuccessful){
                            Toast.makeText(this,"Usuario registrado", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this,"Usuario no registrado", Toast.LENGTH_SHORT).show()
                        }
                        binding.pbBar.visibility = View.GONE
                    }
            }
        }

        binding.toGoLogin.setOnClickListener{
            val intent = Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}