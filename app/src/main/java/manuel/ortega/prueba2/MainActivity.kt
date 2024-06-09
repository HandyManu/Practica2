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
        val txtNumTiket = findViewById<TextView>(R.id.txt_numeroTiket)
        val txtTitulo=findViewById<TextView>(R.id.txt_Titulo)
        val txtDescripcion = findViewById<TextView>(R.id.txt_Descripcion)
        val txtAutor=findViewById<TextView>(R.id.txt_Autor)
        val txtEmail=findViewById<TextView>(R.id.txt_Correo)
        val txtFechaCreacion = findViewById<TextView>(R.id.txt_FechaCreacion)
        val txtEstado = findViewById<TextView>(R.id.txt_Estado)
        val txtFechaFin = findViewById<TextView>(R.id.txt_FechaFinalizacion)
        val btnAgregar = findViewById<Button>(R.id.btnAdd)

        val rcvproductos=findViewById<RecyclerView>(R.id.rcvproductos)

        //asignar un layout al reciledview

        rcvproductos.layoutManager= LinearLayoutManager(this)

        fun obtenerDatos(): List<dataClassTikets> {
            val objConexion = claseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("select * from tb_tiket")!!

            val tiketsList = mutableListOf<dataClassTikets>()
            while (resultSet.next()) {
                val uuid = resultSet.getString("uuid")
                val numero = resultSet.getInt("numero")
                val titulo = resultSet.getString("titulo")
                val descripcion = resultSet.getString("descripcion")
                val autor = resultSet.getString("autor")
                val email = resultSet.getString("email")
                val fechaCreacion = resultSet.getString("fechaCreacion")
                val estado = resultSet.getString("estado")
                val fechaFinalizacion = resultSet.getString("fechaFinalizacion")
                val tiket = dataClassTikets(uuid, numero, titulo, descripcion, autor, email, fechaCreacion, estado, fechaFinalizacion)
                tiketsList.add(tiket)
            }
            return tiketsList
        }

        CoroutineScope ( Dispatchers.IO) .launch {
            val tikets=obtenerDatos()
            withContext(Dispatchers.Main){
                val miAdapter = Adapatador(tikets)
                rcvproductos.adapter=miAdapter
            }
        }

        btnAgregar.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO){

                //guardar datos

                //crear un objeto de la clase conexion
                val claseConexion=claseConexion().cadenaConexion()

                //crar una variable que contenga un preparedstatement

                val addtiket=claseConexion?.prepareStatement("insert into tb_tiket(uuid ,numero,titulo,descripcion,autor,email,fechaCreacion,estado,fechaFin)values(?,?,?,?,?,?,?,?,?)")!!

                addtiket.setString(1,UUID.randomUUID().toString())
                addtiket.setInt(2,txtNumTiket.text.toString().toInt())
                addtiket.setString(3,txtTitulo.text.toString())
                addtiket.setString(4,txtDescripcion.text.toString())
                addtiket.setString(5,txtAutor.text.toString())
                addtiket.setString(6,txtEmail.text.toString())
                addtiket.setString(7,txtFechaCreacion.text.toString())
                addtiket.setString(8,txtEstado.text.toString())
                addtiket.setString(4,txtFechaFin.text.toString())
                addtiket.executeUpdate()

                val nuevosTikets=obtenerDatos()

                withContext(Dispatchers.Main){
                    (rcvproductos.adapter as? Adapatador)?.actualizarLista(nuevosTikets)
                }
            }







    }

}}