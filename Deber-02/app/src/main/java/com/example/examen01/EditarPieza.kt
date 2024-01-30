package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class EditarPieza : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_pieza)

        val idActual = intent.extras?.getInt("idPieza")
        val idDisp = intent.extras!!.getInt("disp")

        if(idActual != null){
            val piezaAEditar = BaseDatos.tablaPiezas!!.consultarPiezaPorID(idActual,idDisp)
            val  id = findViewById<EditText>(R.id.edit_idPieza)
            id.setText(piezaAEditar.idPieza)
            val nombre = findViewById<EditText>(R.id.edit_nombrePieza)
            nombre.setText(piezaAEditar.nombrePieza)
            val peso = findViewById<EditText>(R.id.edit_peso)
            peso.setText(piezaAEditar.peso.toString())
            val  fabricante = findViewById<EditText>(R.id.edit_fabricante)
            fabricante.setText(piezaAEditar.fabricante)
            val  garantia = findViewById<EditText>(R.id.edit_garantia)
            garantia.setText(piezaAEditar.garantia.toString())
        }

        val botonActualizar = findViewById<Button>(R.id.btn_editarPieza)
        botonActualizar.setOnClickListener{

            try{
                val id = findViewById<EditText>(R.id.edit_idPieza)
                val nombre = findViewById<EditText>(R.id.edit_nombrePieza)
                val pes = findViewById<EditText>(R.id.edit_peso)
                val fabricant = findViewById<EditText>(R.id.edit_fabricante)
                val garanti = findViewById<EditText>(R.id.edit_garantia)

                val idPieza = id.text.toString().toInt()
                val nombrePieza = nombre.text.toString()
                val peso  = pes.text.toString().toFloat()
                val fabricante = fabricant.text.toString()
                val garantia = garanti.text.toString().toBoolean()

                BaseDatos.tablaPiezas!!.actualizarPiezaFormulario(idDisp,nombrePieza,peso,garantia,fabricante,idPieza)


                irActividad(ListaPiezas::class.java)


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