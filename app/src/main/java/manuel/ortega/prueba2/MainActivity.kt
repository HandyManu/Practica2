package manuel.ortega.prueba2

import RecicledViwHelper.Adapatador
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.claseConexion
import modelo.dataClassTikets
import java.util.UUID

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtTitulo=findViewById<TextView>(R.id.txt_Titulo)
        val txtDescripcion = findViewById<TextView>(R.id.txt_Descripcion)
        val txtAutor=findViewById<TextView>(R.id.txt_Autor)
        val txtEmail=findViewById<TextView>(R.id.txt_Correo)
        val txtFechaCreacion = findViewById<TextView>(R.id.txt_FechaCreacion)
        val txtEstado = findViewById<TextView>(R.id.txt_Estado)
        val txtFechaFin = findViewById<TextView>(R.id.txt_FechaFinalizacion)
        val btnAgregar = findViewById<Button>(R.id.btnAdd)


        btnAgregar.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO){

                //guardar datos

                //crear un objeto de la clase conexion
                val claseConexion=claseConexion().cadenaConexion()

                //crar una variable que contenga un preparedstatement

                val addtiket=claseConexion?.prepareStatement("insert into tb_tikets(uuidNumero,titulo,descripcion,autor,email,fechaCreacion,estado,fechaFinalizacion)values(?,?,?,?,?,?,?,?,?)")!!

                addtiket.setString(1,UUID.randomUUID().toString())
                addtiket.setString(3,txtTitulo.text.toString())
                addtiket.setString(4,txtDescripcion.text.toString())
                addtiket.setString(5,txtAutor.text.toString())
                addtiket.setString(6,txtEmail.text.toString())
                addtiket.setString(7,txtFechaCreacion.text.toString())
                addtiket.setString(8,txtEstado.text.toString())
                addtiket.setString(4,txtFechaFin.text.toString())
                addtiket.executeUpdate()



            }







    }

}}