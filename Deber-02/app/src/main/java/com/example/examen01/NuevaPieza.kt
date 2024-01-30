package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NuevaPieza : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_pieza)

        val disp = intent.extras?.getString("disp")
        val btnguardar = findViewById<Button>(R.id.btn_guardar_pieza)
        btnguardar.setOnClickListener{
            val id= findViewById<EditText>(R.id.input_idPieza)
            val nombre = findViewById<EditText>(R.id.input_nombre_pieza)
            val peso = findViewById<EditText>(R.id.input_peso)
            val garantia = findViewById<EditText>(R.id.input_garantia)
            val fabricante = findViewById<EditText>(R.id.input_fabricante)

            /*  id.error = null
              nombre.error = null
              fecha.error = null
              precio.error = null
              stock.error = null*/

            val idPieza = id.text.toString().toInt()
            val nombrePieza = nombre.text.toString()
            val pesoPieza  = peso.text.toString().toFloat()
            val garantiaPieza = garantia.text.toString().toBoolean()
            val fabricantePieza = fabricante.text.toString()


            BaseDatos.tablaPiezas!!.crearPieza(idPieza,disp!!.toInt(),nombrePieza,pesoPieza,garantiaPieza,fabricantePieza )

            irActividad(ListaDispositivos::class.java)

        }
    }


    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}