package com.example.examen01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var dispositivos = arrayListOf<Dispositivo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //BaseDatos.tablaDispositivo = ESqliteHelperDispositivo(this)
        //BaseDatos.tablaPiezas = ESqliteHelperPieza(this)

        //Firestore
        BaseDatos.bddAplicacion = DispositivoFirestore()

        BaseDatos.bddAplicacion!!.obtenerDispositivos {
            dispositivos -> this.dispositivos = dispositivos
        }


        val botonInicio = findViewById<Button>(R.id.btn_iniciar)
        botonInicio
            .setOnClickListener {
                irActividad(ListaDispositivos::class.java)
            }


    }



    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
