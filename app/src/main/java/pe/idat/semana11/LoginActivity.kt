package pe.idat.semana11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import pe.idat.semana11.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener{
            var usuario:String  = binding.etCorreo.text.toString()
            var clave : String = binding.etClave.text.toString()

            if(usuario.isNullOrEmpty() || clave.isNullOrEmpty()){
                Toast.makeText(this,"Completa todos los campos",Toast.LENGTH_SHORT).show()
            }else{
                binding.pbBar.visibility = View.VISIBLE
                firebaseAuth.signInWithEmailAndPassword(usuario,clave)
                    .addOnCompleteListener(this){
                        task->
                        if(task.isSuccessful){
                            Toast.makeText(this,"Usuario o clave correctos",Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"Usuario o clave invalidos",Toast.LENGTH_SHORT).show()
                        }
                        binding.pbBar.visibility = View.GONE
                    }
            }
        }

        binding.toGoRegistrar.setOnClickListener{
            val intent = Intent(applicationContext,RegistrarActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}