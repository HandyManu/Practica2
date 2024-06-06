package manuel.ortega.prueba2

import RecicledViwHelper.Adapatador
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.claseConexion
import java.util.UUID

class Activity_Registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrarse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtCorreo = findViewById<EditText>(R.id.txt_correoRegistro)
        val txtClave = findViewById<EditText>(R.id.txt_claveRegistro)
        val btnRegistro = findViewById<Button>(R.id.btn_Registarse)
        val imgRegistro =findViewById<ImageView>(R.id.img_regresar)

        btnRegistro.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO){

                val objConexion = claseConexion().cadenaConexion()

                val crearUsuario =  objConexion?.prepareStatement("INSERT IN TO Tb_usaurio (email , clave ) VALUES (?,?)")!!

                crearUsuario.setString(1,txtCorreo.text.toString())
                crearUsuario.setString(2,txtClave.text.toString())
                crearUsuario.executeUpdate()
                withContext(Dispatchers.Main){
                    Toast.makeText(this@Activity_Registrarse,"usuario creado",Toast.LENGTH_SHORT).show()
                    txtCorreo.setText("")
                    txtClave.setText("")
                }
            }
        }

        imgRegistro.setOnClickListener {
            val activityLogin = Intent(this,Activity_Login::class.java)
            startActivity(activityLogin)


        }






    }
}