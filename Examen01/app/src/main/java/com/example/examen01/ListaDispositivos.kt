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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class ListaDispositivos : AppCompatActivity() {
    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    // Logica Negocio
                    val data = result.data
                }
            }
        }
    val arreglo = BaseDatosDispositivo.arregloBDispositivo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_dispositivos)

        val listView = findViewById<ListView>(R.id.lv_lista_disp)
        val adaptador = ArrayAdapter(
            this, // Contexto
            // como se va a ver (XML)
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()


        registerForContextMenu(listView)


        val botonCrear = findViewById<Button>(R.id.btn_nuevo_disp)
        botonCrear.setOnClickListener { irActividad(NuevoDispositivo::class.java) }


    }

    var posicionItemSeleccionado = 0
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                abrirActividadConParametros(EditarDispositivo::class.java, posicionItemSeleccionado)
                return true
            }

            R.id.mi_eliminar -> {
                abrirDialogo(posicionItemSeleccionado)
                return true
            }

            R.id.mi_verPiezas -> {
                abrirActividadConParametros(ListaPiezas::class.java, posicionItemSeleccionado)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(posicionDispositivo: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Esta seguro que desea eliminar?")
        builder.setPositiveButton("Aceptar",
            DialogInterface.OnClickListener { dialog, which ->
                val id = posicionDispositivo + 1
                eliminarDispositivo(id)
                dialog.dismiss()
                irActividad(ListaDispositivos::class.java)

            }
        )

        builder.setNegativeButton("Cancelar", null)
        val dialogo = builder.create()
        dialogo.show()
    }
    fun eliminarDispositivo(id: Int){
        // Buscar el dispositivo a eliminar
        val dispositivoAEliminar = arreglo.find { it.idDispositivo == id }
        // Si se encontró el dispositivo
        if (dispositivoAEliminar != null) {
            // Eliminar el dispositivo del arreglo
            arreglo.remove(dispositivoAEliminar)
        } else {
            // No se encontró el dispositivo
            println("No se encontró un dispositivo con el ID $id.")
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirActividadConParametros(clase: Class<*>, dispositivoEditar: Int) {
        val intentExplicito = Intent(this, clase)
        // Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("id", dispositivoEditar)

        callbackContenidoIntentExplicito.launch(intentExplicito)
    }

}