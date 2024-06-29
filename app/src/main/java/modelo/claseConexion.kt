package modelo

import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager

class claseConexion {
    fun cadenaConexion(): Connection?{
        try {
            val url="jdbc:oracle:thin:@192.168.1.21:1521:xe"
            val user="SYSTEM"
            val password="ITR2024"
            val connection= DriverManager.getConnection(url, user, password)
            return connection
        }catch (e: Exception){
            println("este es el error:$e")
            return null
        }
    }
}