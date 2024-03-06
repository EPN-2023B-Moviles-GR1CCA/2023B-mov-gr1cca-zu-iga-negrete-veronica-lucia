package com.example.examen01

import android.app.Activity
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
import com.google.android.material.snackbar.Snackbar

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
                //obtenerDispositivos()
                dispositivos
            )
            listView.adapter = adaptador
            adaptador.notifyDataSetChanged()

            registerForContextMenu(listView)


        val botonCrear = findViewById<Button>(R.id.btn_nuevo_disp)
        botonCrear.setOnClickListener { irActividad(NuevoDispositivo::class.java) }


    }

    fun mostrarSnackbar(texto:String){
        Snackbar
            .make(
                findViewById(R.id.lv_lista_disp), //view
                texto, //texto
                Snackbar.LENGTH_LONG //tiwmpo
            )
            .show()
    }
    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {

                    val data = result.data
                    dispositivos = BaseDatos.tablaDispositivo!!.listaDispositivos()
                    val adaptador = ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        dispositivos
                    )
                    val listView = findViewById<ListView>(R.id.lv_lista_disp)
                    listView.adapter = adaptador
                    adaptador.notifyDataSetChanged()
                    registerForContextMenu(listView)
                    mostrarSnackbar("${data?.getStringExtra("message")}")




                }
            }
        }


    var idDispo = 0
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
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
            R.id.mi_editar_dispo -> {
                "${idDispo}"
                val id = dispositivos.get(idDispo).idDispositivo
                val extras = Bundle()
                extras.putString("idDispositivo", id.toString())
              //  abrirActividadConParametros(EditarDispositivo::class.java, idDispo)
                editarDisp(EditarDispositivo::class.java, extras)
                return true
            }

            R.id.mi_eliminar_dispo -> {
                "${idDispo}"
                abrirDialogo(dispositivos.get(idDispo).idDispositivo)
                return true
            }

            R.id.mi_verPiezas -> {
                val disp = dispositivos.get(idDispo).idDispositivo
                val nombreDisp = dispositivos.get(idDispo).nombreDispositivo
                val extras = Bundle()
                extras.putString("disp", disp.toString())
                extras.putString("nombreDispositivo",nombreDisp)
                editarDisp(ListaPiezas::class.java, extras)
            //    abrirActividadConParametros(ListaPiezas::class.java, idDispo)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(idDispositivo: Int) {
        val builder = AlertDialog.Builder(this)
        var eliminacionExitosa = false
        builder.setTitle("¿Esta seguro que desea eliminar?")
        builder.setPositiveButton("Aceptar",
            { dialog, which ->
              /* val resp = BaseDatos.tablaDispositivo?.eliminarDispositivoFormulario(idDispositivo)
                if(resp == true){
                    mostrarSnackbar("Se ha eliminado")
                    dispositivos = BaseDatos.tablaDispositivo!!.listaDispositivos()
                    val adaptador = ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        dispositivos
                    )
                    val listView = findViewById<ListView>(R.id.lv_lista_disp)
                    listView.adapter = adaptador
                    adaptador.notifyDataSetChanged()
                    registerForContextMenu(listView)
                    eliminacionExitosa = true
                }else{
                    mostrarSnackbar("No se ha eliminado")
                    eliminacionExitosa = false
                }
                //val id = posicionDispositivo
                //BaseDatos.tablaDispositivo!!.eliminarDispositivoFormulario(id)
                //adaptador.notifyDataSetChanged()*/
                BaseDatos.bddAplicacion?.eliminarDispPorID(idDispositivo.toString())
                    ?.addOnSuccessListener {
                        val listView = findViewById<ListView>(R.id.lv_lista_disp)
                        listView.adapter = adaptador
                        adaptador.notifyDataSetChanged()
                        registerForContextMenu(listView)
                        eliminacionExitosa = true
                    }
                    ?.addOnFailureListener { e ->
                        mostrarSnackbar("No se pudo eliminar al cocinero")
                        eliminacionExitosa = false
                    }
            }
        )
        builder.setNegativeButton("Cancelar", null)

        val dialogo = builder.create()
        dialogo.show()

    }
  /*  fun eliminarDispositivo(id: Int){
       BaseDatos.tablaDispositivo!!.eliminarDispositivoFormulario(
           id)
        adaptador.notifyDataSetChanged()

        irActividad(ListaDispositivos::class.java)
    }*/

  /*  fun obtenerDispositivos(): ArrayList<Dispositivo> {
        return BaseDatos.tablaDispositivo!!.listaDispositivos()
    }*/

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
    fun editarDisp(clase: Class<*>, parametros: Bundle? = null){
        val intent = Intent(this, clase)
        if(parametros != null){
            intent.putExtras(parametros)
        }
        callbackContenidoIntentExplicito.launch(intent)
    }
   /* fun irActividad(clase: Class<*>, id: Int){
        val intent = Intent(this, clase)
        intent.putExtra("id", id)
        startActivity(intent)
    }*/

  /* fun abrirActividadConParametros(clase: Class<*>, dispositivoSeleccion: Int) {
        val intentExplicito = Intent(this, clase)
        // Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("id", dispositivoSeleccion)

        callbackContenidoIntentExplicito.launch(intentExplicito)
    }*/


}