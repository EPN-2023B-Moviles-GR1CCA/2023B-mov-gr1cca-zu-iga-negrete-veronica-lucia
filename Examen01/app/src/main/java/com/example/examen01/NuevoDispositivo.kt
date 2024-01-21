package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner;
import android.widget.Toast;

class NuevoDispositivo : AppCompatActivity() {
    val arreglo = BaseDatosDispositivo.arregloBDispositivo



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_dispositivo)
       /* val opciones = resources.getStringArray(R.array.opciones)

        val spinner =  findViewById<Spinner>(R.id.input_stock)
        spinner.adapter = ArrayAdapter(this, R.layout.activity_nuevo_dispositivo, opciones)*/


        val btnguardar = findViewById<Button>(R.id.btn_guardar_disp)
        btnguardar.setOnClickListener{
            val id = findViewById<EditText>(R.id.input_idDisp)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val fecha = findViewById<EditText>(R.id.input_fecha)
            val precio = findViewById<EditText>(R.id.input_precio)
            val stock = findViewById<EditText>(R.id.input_stock)

            val idDipositivo = id.text.toString().toInt()
            val nombreDispositivo = nombre.text.toString()
            val fechaCreacion  = fecha.text.toString()
            val precioDispositivo = precio.text.toString().toFloat()
            val stockDispositivo = stock.text.toString().toBoolean()


            BaseDatos.tablaDispositivo!!.crearDispositivo(nombreDispositivo,
                fechaCreacion,stockDispositivo,precioDispositivo)

            irActividad(ListaDispositivos::class.java)

        }
    }


    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}