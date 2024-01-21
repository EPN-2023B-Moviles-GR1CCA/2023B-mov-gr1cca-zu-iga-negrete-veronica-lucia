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
import java.text.SimpleDateFormat

class ListaDispositivos : AppCompatActivity() {
    private lateinit var adaptador: ArrayAdapter<Dispositivo>

    var listaDispositivo = BaseDatos.tablaDispositivo!!.listaDispositivos()
    var dispositivos= arrayListOf<Dispositivo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_dispositivos)

        dispositivos = BaseDatos.tablaDispositivo!!.listaDispositivos()



        val listView = findViewById<ListView>(R.id.lv_lista_disp)
        val adaptador = ArrayAdapter(
            this, // Contexto
            // como se va a ver (XML)
            android.R.layout.simple_list_item_1,
            obtenerDispositivos()
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(listView)


        val botonCrear = findViewById<Button>(R.id.btn_nuevo_disp)
        botonCrear.setOnClickListener { irActividad(NuevoDispositivo::class.java) }


    }
    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    // Logica Negocio
                    val data = result.data
                    val nombreUp = data?.getStringExtra("nombreModificado")
                    val stockUp = data?.getBooleanExtra("stockModificado", false)
                    val precioUp = data?.getFloatExtra("precioModificado", 0.0f)
                    val fechaUp = data?.getStringExtra("dateModificado")

                    val formato = SimpleDateFormat("yyyy-MM-dd")
                    val fechaNueva = formato.parse(fechaUp)
                    val nombreDispositivo = nombreUp.toString()
                    val stock = stockUp.toString().toBoolean()
                    val precio = precioUp
                    val fechaCreacion = fechaNueva.toString()

                    val id = idDispo

                    BaseDatos.tablaDispositivo!!.actualizarDipositivoFormulario(
                        nombreDispositivo,fechaCreacion,stock, precio, id)


                    for (disp in obtenerDispositivos()){
                        println("ID: ${disp.idDispositivo}")
                        println("Nombre: ${disp.nombreDispositivo}")
                        println("Stock: ${disp.stock}")
                        println("Precio: ${disp.precio}")
                    }

                    adaptador.notifyDataSetChanged()
                    irActividad(ListaDispositivos::class.java)



                }
            }
        }


    var idDispo = 0
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
        idDispo = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                "${idDispo}"
                abrirActividadConParametros(EditarDispositivo::class.java, idDispo)
                return true
            }

            R.id.mi_eliminar -> {
                "${idDispo}"
                abrirDialogo(dispositivos.get(idDispo).idDispositivo)
                return true
            }

            R.id.mi_verPiezas -> {
                "${idDispo}"
                abrirActividadConParametros(ListaPiezas::class.java, idDispo)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(posicionDispositivo: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Esta seguro que desea eliminar?")
        builder.setPositiveButton("Aceptar",
            { dialog, which ->
                val id = posicionDispositivo
                BaseDatos.tablaDispositivo!!.eliminarDispositivoFormulario(id)
                adaptador.notifyDataSetChanged()
                dialog.dismiss()
                irActividad(ListaDispositivos::class.java)

            }
        )
        builder.setNegativeButton("Cancelar", null)
        val dialogo = builder.create()
        dialogo.show()
    }
    fun eliminarDispositivo(id: Int){
       BaseDatos.tablaDispositivo!!.eliminarDispositivoFormulario(
           id)
        adaptador.notifyDataSetChanged()

        irActividad(ListaDispositivos::class.java)
    }

    fun obtenerDispositivos(): ArrayList<Dispositivo> {
        return BaseDatos.tablaDispositivo!!.listaDispositivos()
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
   /* fun irActividad(clase: Class<*>, id: Int){
        val intent = Intent(this, clase)
        intent.putExtra("id", id)
        startActivity(intent)
    }*/

   fun abrirActividadConParametros(clase: Class<*>, dispositivoSeleccion: Int) {
        val intentExplicito = Intent(this, clase)
        // Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("id", dispositivoSeleccion)

        callbackContenidoIntentExplicito.launch(intentExplicito)
    }


}