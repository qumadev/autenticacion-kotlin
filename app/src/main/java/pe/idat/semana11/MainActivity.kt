package pe.idat.semana11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import pe.idat.semana11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseUser: FirebaseUser
    var db = FirebaseFirestore.getInstance()
    lateinit var telefono: String
    lateinit var pais: String
    lateinit var correo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser!!

        if (firebaseUser == null){
            goLogin()
        } else{
            correo = firebaseUser.email.toString()
            binding.tvUsuario.text = correo
        }

        binding.btnRegistrar.setOnClickListener {
            telefono = binding.etTelefono.text.toString()
            pais = binding.etPais.text.toString()
            if (telefono.isNullOrEmpty() || pais.isNullOrEmpty()) {
                Toast.makeText(this,"Debes ingresar el telefono y el pais",Toast.LENGTH_SHORT).show()
            } else {
                db.collection("usuarios")
                    .document(correo)
                    .set(
                        hashMapOf("telefono" to telefono,
                            "pais" to pais)
                    )
            }
        }

        binding.btnObtener.setOnClickListener {
            db.collection("usuarios")
                .document(correo)
                .get().addOnSuccessListener { document ->
                    binding.etTelefono.setText(document.get("telefono").toString())
                    binding.etPais.setText(document.get("pais").toString())
                }
        }

        binding.btnModificar.setOnClickListener {

        }
        binding.btnEliminar.setOnClickListener {  }

        binding.btnCerrarSesion.setOnClickListener {
            FirebaseAuth.getInstance().signOut();
            goLogin();
        }

    }

    fun goLogin()
    {
        val intent = Intent(applicationContext,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}