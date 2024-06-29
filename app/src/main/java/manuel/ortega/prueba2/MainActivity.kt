package manuel.ortega.prueba2
import RecicledViwHelper.Adaptador
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
        val txtTitulo = findViewById<TextView>(R.id.txt_Titulo)
        val txtDescripcion = findViewById<TextView>(R.id.txt_Descripcion)
        val txtAutor = findViewById<TextView>(R.id.txt_Autor)
        val txtEmail = findViewById<TextView>(R.id.txt_Correo)
        val txtFechaCreacion = findViewById<TextView>(R.id.txt_FechaCreacion)
        val txtEstado = findViewById<TextView>(R.id.txt_Estado)
        val txtFechaFin = findViewById<TextView>(R.id.txt_FechaFinalizacion)
        val btnAgregar = findViewById<Button>(R.id.btnAdd)
        val rcvTikets = findViewById<RecyclerView>(R.id.rcvtikets)

        rcvTikets.layoutManager = LinearLayoutManager(this)

        fun obtenerTikets(): List<dataClassTikets> {
            val objConexion = claseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("SELECT * FROM tb_tikets")!!

            val listaTikets = mutableListOf<dataClassTikets>()
            while (resultSet.next()) {
                val uuidNumero = resultSet.getString("uuidNumero")
                val titulo = resultSet.getString("titulo")
                val descripcion = resultSet.getString("descripcion")
                val autor = resultSet.getString("autor")
                val email = resultSet.getString("email")
                val fechaCreacion = resultSet.getString("fechaCreacion")
                val estado = resultSet.getString("estado")
                val fechaFinalizacion = resultSet.getString("fechaFinalizacion")

                val valorTikets = dataClassTikets(uuidNumero, titulo, descripcion, autor, email, fechaCreacion, estado, fechaFinalizacion)
                listaTikets.add(valorTikets)
            }
            return listaTikets
        }

        CoroutineScope(Dispatchers.IO).launch {
            val tiketsDatos = obtenerTikets()
            withContext(Dispatchers.Main) {
                val adapter = Adaptador(tiketsDatos)
                rcvTikets.adapter = adapter
            }
        }

        btnAgregar.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val claseConexion = claseConexion().cadenaConexion()
                val addTiket = claseConexion?.prepareStatement("INSERT INTO tb_tikets(uuidNumero, titulo, descripcion, autor, email, fechaCreacion, estado, fechaFinalizacion) VALUES(?, ?, ?, ?, ?, ?, ?, ?)")!!

                addTiket.setString(1, UUID.randomUUID().toString())
                addTiket.setString(2, txtTitulo.text.toString())
                addTiket.setString(3, txtDescripcion.text.toString())
                addTiket.setString(4, txtAutor.text.toString())
                addTiket.setString(5, txtEmail.text.toString())
                addTiket.setString(6, txtFechaCreacion.text.toString())
                addTiket.setString(7, txtEstado.text.toString())
                addTiket.setString(8, txtFechaFin.text.toString())
                addTiket.executeUpdate()

                val nuevosTikets = obtenerTikets()
                withContext(Dispatchers.Main) {
                    (rcvTikets.adapter as? Adaptador)?.actualizarLista(nuevosTikets)
                }
            }
        }


    }

}