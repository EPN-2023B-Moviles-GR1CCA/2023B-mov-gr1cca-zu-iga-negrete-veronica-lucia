package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.text.SimpleDateFormat

class EditarDispositivo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_dispositivo)

        val id = intent.getIntExtra("id",0)

        val disp = BaseDatos.tablaDispositivo!!.consultarDispositivoPorID(id)

        val nombre = findViewById<EditText>(R.id.edit_nombre)
        val nombreActual = disp.nombreDispositivo.toString()
        nombre.setText(nombreActual)

        val fecha = findViewById<EditText>(R.id.edit_fecha)
        val fechaInicial = disp.fechaCreacion.toString()
        fecha.setText(fechaInicial)

        val  precio = findViewById<EditText>(R.id.edit_precio)
        val precioActual = disp.precio.toString()
        precio.setText(precioActual)

        val  stock = findViewById<EditText>(R.id.edit_precio)
        val stockActual = disp.stock.toString()
        stock.setText(stockActual)

        val botonActualizar = findViewById<Button>(R.id.btn_actualizar)
        botonActualizar.setOnClickListener{
            val nombre = nombre.text.toString()
            val fecha = fecha.text.toString()
            val precio = precio.text.toString()
            val stock = stock.text.toString().toBoolean()

            val intentParam = Intent()

            intentParam.putExtra("nombreEditado",nombre)
            intentParam.putExtra("fechaEditado",fecha)
            intentParam.putExtra("precioEditado",precio.toFloat())
            intentParam.putExtra("stockEditado",stock)

            BaseDatos.tablaDispositivo!!.actualizarDipositivoFormulario(
                nombre,fecha,stock,precio.toFloat(),id
            )
            setResult(
                RESULT_OK,
                intentParam
            )
            finish()
        }




    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent)
    }
}