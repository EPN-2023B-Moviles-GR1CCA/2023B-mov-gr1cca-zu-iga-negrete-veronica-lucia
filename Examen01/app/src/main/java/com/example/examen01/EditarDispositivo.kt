package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat

class EditarDispositivo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_dispositivo)

        val idActual = intent.extras?.getInt("idDispositivo")
        val name = intent.extras?.getString("nombreDispositivo")

        if(idActual != null){
            val dipAEditar = BaseDatos.tablaDispositivo!!.consultarDispositivoPorID(idActual)
            val  id = findViewById<EditText>(R.id.input_idDisp)
            id.setText(dipAEditar.idDispositivo)
            val nombre = findViewById<EditText>(R.id.edit_nombre)
            nombre.setText(dipAEditar.nombreDispositivo)
            val fecha = findViewById<EditText>(R.id.edit_fecha)
            fecha.setText(dipAEditar.fechaCreacion)
            val  precio = findViewById<EditText>(R.id.edit_precio)
            precio.setText(dipAEditar.precio.toString())
            val  stock = findViewById<EditText>(R.id.edit_stock)
            stock.setText(dipAEditar.stock.toString())
        }

        val botonActualizar = findViewById<Button>(R.id.btn_actualizar)
        botonActualizar.setOnClickListener{

            try{
                val idDisp = findViewById<EditText>(R.id.input_idDisp)
                val nombre = findViewById<EditText>(R.id.input_nombre)
                val fecha = findViewById<EditText>(R.id.input_fecha)
                val precio = findViewById<EditText>(R.id.input_precio)
                val stock = findViewById<EditText>(R.id.input_stock)

                val idDipositivo = idDisp.text.toString().toInt()
                val nombreDispositivo = nombre.text.toString()
                val fechaCreacion  = fecha.text.toString()
                val precioDispositivo = precio.text.toString().toFloat()
                val stockDispositivo = stock.text.toString().toBoolean()


                BaseDatos.tablaDispositivo!!.actualizarDipositivoFormulario(nombreDispositivo,
                    fechaCreacion,stockDispositivo,precioDispositivo, idDipositivo)


                irActividad(ListaDispositivos::class.java)


            }catch (e: Exception) {
                Log.e("Error", "Error en la aplicación", e)
            }
        }



    }


    fun irActividad(
        clase: Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent)
    }
}