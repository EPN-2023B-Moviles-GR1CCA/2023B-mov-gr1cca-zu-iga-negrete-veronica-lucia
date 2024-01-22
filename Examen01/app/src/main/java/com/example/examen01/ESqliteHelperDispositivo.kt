package com.example.examen01

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperDispositivo(contexto: Context?,) : SQLiteOpenHelper(contexto, "moviles", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaDispositivo =
            """
                CREATE TABLE DISPOSITIVO(
                idDispositivo INTEGER PRIMARY KEY,
                nombreDispositivo VARCHAR(50),
                fechaCreacion VARCHAR(50),
                stock BOOLEAN,
                precio REAL
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaDispositivo)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun crearDispositivo( id: Int, nombreDispositivo: String, fechaCreacion: String, stock: Boolean, precio: Float,): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("idDispositivo", id)
        valoresAGuardar.put("nombreDispositivo", nombreDispositivo)
        valoresAGuardar.put("fechaCreacion", fechaCreacion)
        valoresAGuardar.put("stock", stock)
        valoresAGuardar.put("precio", precio)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "DISPOSITIVO",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun listaDispositivos(): ArrayList<Dispositivo> {
        val baseDatosLectura = readableDatabase
        val arreglo = arrayListOf<Dispositivo>()
        val scriptConsultaLectura = """
            SELECT * FROM DISPOSITIVO 
        """.trimIndent()
        val cursor = baseDatosLectura.rawQuery(scriptConsultaLectura, null)
        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val fecha = cursor.getString(2)
                val stock = cursor.getString(3)
                val precio = cursor.getString(4)
                val dispositivo = Dispositivo(id,nombre,fecha,stock.toBoolean(),precio.toFloat())
                arreglo.add(dispositivo)
            }while (cursor.moveToNext())
        }
        cursor.close()
        baseDatosLectura.close()
        return arreglo
    }

    fun eliminarDispositivoFormulario(id: Int):Boolean{
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "DISPOSITIVO",
                "idDispositivo=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if(resultadoEliminacion == -1) false else true
    }

    fun actualizarDipositivoFormulario(nombreDispositivo: String, fechaCreacion: String, stock: Boolean, precio: Float?,id:Int, ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombreDispositivo", nombreDispositivo)
        valoresAActualizar.put("fechaCreacion", fechaCreacion)
        valoresAActualizar.put("stock", stock)
        valoresAActualizar.put("precio", precio)
        // where ID = ?
        val parametrosConsultaActualizar = arrayOf( id.toString() )
        val resultadoActualizacion = conexionEscritura
            .update(
                "DISPOSITIVO", // Nombre tabla
                valoresAActualizar, // Valores
                "idDispositivo=?", // Consulta Where
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if(resultadoActualizacion == -1) false else true
    }

    fun consultarDispositivoPorID(id: Int): Dispositivo{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM DISPOSITIVO WHERE idDispositivo = ?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura, // Consulta
            parametrosConsultaLectura, // Parametros
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = Dispositivo(0, "" , "",false,0.0f)
        val arreglo = arrayListOf<Dispositivo>()
        if(existeUsuario){
            do{
                val idDispositivo= resultadoConsultaLectura.getInt(0) // Indice 0
                val nombreDispositivo = resultadoConsultaLectura.getString(1)
                val fechaCreacion = resultadoConsultaLectura.getString(2)
                val stock = resultadoConsultaLectura.getString(3)
                val precio = resultadoConsultaLectura.getString(4)
                if(idDispositivo != null){
                    // val usuarioEncontrado = BEntrenador(0, "" , "")
                    usuarioEncontrado.idDispositivo = idDispositivo
                    usuarioEncontrado.nombreDispositivo= nombreDispositivo
                    usuarioEncontrado.fechaCreacion = fechaCreacion
                    usuarioEncontrado.stock = stock.toBoolean()
                    usuarioEncontrado.precio = precio.toFloat()
                    // arreglo.add(usuarioEncontrado)
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado //arreglo
    }

    fun retrieveAllDispositivos(): ArrayList<Dispositivo> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
        SELECT * FROM DISPOSITIVO
    """.trimIndent()

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, // Consulta
            null // No parameters needed for all items
        )

        val dispList = ArrayList<Dispositivo>()

        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val idDispositivo = resultadoConsultaLectura.getInt(0)
                val nombreDispositivo = resultadoConsultaLectura.getString(1)
                val fechaCreacion = resultadoConsultaLectura.getString(2)
                val stock = resultadoConsultaLectura.getInt(3) != 0
                val precio = resultadoConsultaLectura.getFloat(4)

                val dipositivo = Dispositivo(idDispositivo, nombreDispositivo, fechaCreacion, stock, precio)
                dispList.add(dipositivo)
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return dispList
    }







}