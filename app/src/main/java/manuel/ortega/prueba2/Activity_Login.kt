package manuel.ortega.prueba2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.claseConexion

class Activity_Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//mando a llamar a todos los elementos de la pantalla

        val txtEmail = findViewById<TextView>(R.id.txt_CorreoLogin)
        val  txtClave = findViewById<TextView>(R.id.txt_ClaveLogin)
        val btnLogin = findViewById<Button>(R.id.btn_Ingresar)

        btnLogin.setOnClickListener{
            val mainActivity = Intent ( this,MainActivity :: class .java)
            GlobalScope.launch(Dispatchers.IO){
                val objconexion = claseConexion().cadenaConexion()
                val buscarUsuario = objconexion?.prepareStatement("select * from Tb_usaurio where email =? and clave =?")!!
                buscarUsuario.setString(1,txtEmail.text.toString())
                buscarUsuario.setString(2,txtClave.text.toString())

                val buscar = buscarUsuario.executeQuery()

                if (buscar.next()) {
                println("Si se encontro el usuario")
                    startActivity(mainActivity)
                }else{
                    println("No se encontro el usuario")
                }
            }
        }
    }
}