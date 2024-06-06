package manuel.ortega.prueba2

import RecicledViwHelper.Adapatador
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
        val txtcorreo = findViewById<TextView>(R.id.txt_CorreoLogin)
        val txtclave = findViewById<TextView>(R.id.txt_ClaveLogin)
        val btnRegistrarse = findViewById<Button>(R.id.tv_Registrarse)

        btnRegistrarse.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO){

                //guardar datos

                //crear un objeto de la clase conexion
                val claseConexion=claseConexion().cadenaConexion()

                //crar una variable que contenga un preparedstatement

                val addUser=claseConexion?.prepareStatement("insert into Tb_usaurio (email,clave)values(?,?)")!!


                addProducto.setString(1,txtcorreo.text.toString())
                addProducto.setInt(2,txtclave.text.toString())
                addProducto.executeUpdate()

                val nuevosProductos=obtenerDatos()

                withContext(Dispatchers.Main){
                    (rcvproductos.adapter as? Adapatador)?.actualizarLista(nuevosProductos)
                }
            }
    }
}