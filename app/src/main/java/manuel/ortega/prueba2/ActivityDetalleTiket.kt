package manuel.ortega.prueba2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityDetalleTiket : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_tiket)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtNumero = findViewById<TextView>(R.id.txtNumero)
        val txtTitulo = findViewById<TextView>(R.id.txtTitulo)
        val txtDescripcion = findViewById<TextView>(R.id.txtDescripcion)
        val txtAutor = findViewById<TextView>(R.id.txtAutor)
        val txtMail = findViewById<TextView>(R.id.txtmail)
        val txtFechaCrea = findViewById<TextView>(R.id.txtFechaCreacion)
        val txtEstado = findViewById<TextView>(R.id.txtEstado)
        val txtFechaFin = findViewById<TextView>(R.id.txtFechaFin)
        val txtuuid = findViewById<TextView>(R.id.txtUUID)
        val imgAtras = findViewById<ImageView>(R.id.imgAtras)

        val numeroTiket = intent.getIntExtra("numero",0)
        val tituloTiket = intent.getStringExtra("titulo")
        val DescripcionTiket = intent.getStringExtra("descripcion")
        val autorTikets = intent.getStringExtra("autor")
        val email = intent.getStringExtra("email")
        val fechaCreacion = intent.getStringExtra("fechaCreacion")
        val estadoTiket = intent.getStringExtra("estado")
        val fechaFinal = intent.getStringExtra("fechaFinalizacion")
        val uuid = intent.getStringExtra("uuid")

        txtNumero.text=numeroTiket.toString()
        txtTitulo.text=tituloTiket
        txtDescripcion.text=DescripcionTiket
        txtAutor.text=autorTikets
        txtMail.text=email
        txtFechaCrea.text=fechaCreacion
        txtEstado.text=estadoTiket
        txtFechaFin.text=fechaFinal
        txtuuid.text=uuid

        imgAtras.setOnClickListener {
            val pantallaAtras = Intent(this,MainActivity::class.java)
            startActivity(pantallaAtras)
        }


}}