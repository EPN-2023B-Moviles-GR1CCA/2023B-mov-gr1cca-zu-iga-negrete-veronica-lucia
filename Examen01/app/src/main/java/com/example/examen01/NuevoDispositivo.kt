package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

class NuevoDispositivo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_dispositivo)
        val opciones = resources.getStringArray(R.array.opciones)

        val spinner =  findViewById<Spinner>(R.id.input_stock)
        spinner.adapter = ArrayAdapter(this, R.layout.activity_nuevo_dispositivo, opciones)










    }


    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}