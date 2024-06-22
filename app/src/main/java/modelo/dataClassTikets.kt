package modelo

import android.provider.ContactsContract.CommonDataKinds.Email

data class dataClassTikets(
    val uuidNumero:String,
    var titulo:String,
    var descripcion:String,
    var autor : String,
    var email: String,
    val fechaCreacion : String,
    var estado :String,
    var fechaFinalizacion:String

)
