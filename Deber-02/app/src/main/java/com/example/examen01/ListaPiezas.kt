package com.example.examen01

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class ListaPiezas : AppCompatActivity() {
    var piezas = arrayListOf<Pieza>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_piezas)

       // BaseDatos.tablaPiezas = ESqliteHelperPieza(this)

        val idDispo = intent.extras?.getString("disp")
        val nombre = intent.extras?.getString("nombreDispositivo")

        findViewById<TextView>(R.id.id_dispositivo).setText(nombre)
        if (idDispo != null) {
            piezas = BaseDatos.tablaPiezas!!.consultarPiezasPorForanea(idDispo.toInt())
            if (piezas.size != 0) {

                val listView = findViewById<ListView>(R.id.lv_lista_piezas)

                val adaptador = ArrayAdapter(
                    this, // contexto
                    android.R.layout.simple_list_item_1, // como se va a ver (XML)
                    piezas
                )

                listView.adapter = adaptador
                adaptador.notifyDataSetChanged()
                registerForContextMenu(listView)
            } else {

            }
            val btnCrearPieza = findViewById<Button>(R.id.btn_anadir)
            btnCrearPieza
                .setOnClickListener {
                    val extras = Bundle()
                    extras.putString("disp", idDispo)
                    irActividad(NuevaPieza::class.java, extras)
                }


        }


    }

    var posicionItemSeleccionado = 0
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_pieza, menu)
        //obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar_pieza ->{
                val idPieza = piezas.get(posicionItemSeleccionado).idPieza
                val nombrePieza= piezas.get(posicionItemSeleccionado).nombrePieza
                val disp = piezas.get(posicionItemSeleccionado).disp
//                mostrarSnackbar(identificador)
                val extras = Bundle()
                extras.putString("idPieza", idPieza.toString())
                extras.putString("disp", disp.toString())
                extras.putString("nombrePieza", nombrePieza)
                irActividad(EditarPieza::class.java, extras)
                return true
            }
            R.id.mi_eliminar_pieza-> {
                val idPieza = piezas.get(posicionItemSeleccionado).idPieza
                val disp = piezas.get(posicionItemSeleccionado).disp
//                mostrarSnackbar(identificador)
                val result: Boolean = abrirDialogo(idPieza, disp)
                if(result) true else

                    return false
            }
            else -> super.onContextItemSelected(item)
        }


    }


    fun abrirDialogo(idPieza: Int, disp: Int): Boolean {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Esta seguro que desea eliminar?")

        var eliminacionExitosa = false

        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog, which ->

                val respuesta = BaseDatos.tablaPiezas?.eliminarPiezaFormulario(idPieza, disp)

                if (respuesta == true) {

                    cargarListaDispositivos(disp.toString())
                    eliminacionExitosa = true
                } else {

                    eliminacionExitosa = false
                }
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()

        return eliminacionExitosa
    }
    private fun cargarListaDispositivos(disp: String) {
        // Cargar la lista de comidas del cocinero desde la base de datos y notificar al adaptador
        piezas = BaseDatos.tablaPiezas!!.consultarPiezasPorForanea(disp.toInt())
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            piezas
        )
        val listView = findViewById<ListView>(R.id.lv_lista_piezas)
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
    }

   /* fun mostrarSnackbar(texto: String) {
        Snackbar
            .make(
                findViewById(androidx.constraintlayout.widget), //view
                texto, //texto
                Snackbar.LENGTH_LONG //tiwmpo
            )
            .show()
    }*/

    fun irActividad(clase: Class<*>, datosExtras: Bundle? = null) {
        val intent = Intent(this, clase)
        if (datosExtras != null) {
            intent.putExtras(datosExtras)
        }
//        startActivity(intent)
        callbackContenidoIntentExplicito.launch(intent)
    }
    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    val data = result.data
                    cargarListaDispositivos("${data?.getStringExtra("disp")}")

                }
            }
        }

}
