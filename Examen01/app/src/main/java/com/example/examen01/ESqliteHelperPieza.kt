package com.example.examen01

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperPieza(contexto: Context?,) : SQLiteOpenHelper(contexto, "moviles2", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaPieza =
            """
            CREATE TABLE PIEZA (
            idPieza INTEGER PRIMARY KEY,
            disp INTEGER,
            nombrePieza VARCHAR(50),
            peso REAL,
            garantia BOOLEAN,
            fabricante VARCHAR(50),
            CONSTRAINT fk_dispositivo
                    FOREIGN KEY (disp)
                    REFERENCES DISPOSITIVO(disp)
                    ON DELETE CASCADE
        )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaPieza)
        Log.d("Database", "PIEZA table created")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}



    fun crearPieza(idPieza: Int, disp: Int, nombrePieza: String, peso: Float, garantia: Boolean, fabricante: String): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()

        valoresAGuardar.put("idPieza", idPieza)
        valoresAGuardar.put("nombrePieza", nombrePieza)
        valoresAGuardar.put("peso", peso)
        valoresAGuardar.put("garantia", garantia)
        valoresAGuardar.put("fabricante", fabricante)
        valoresAGuardar.put("disp", disp)

        val resultadoGuardar = basedatosEscritura
            .insert(
                "PIEZA", // Nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }



    fun eliminarPiezaFormulario(idPieza:Int, idDisp: Int):Boolean{
        val conexionEscritura = writableDatabase
        // where ID = ?
        val parametrosConsultaDelete = arrayOf( idPieza.toString(), idDisp.toString() )
        val resultadoEliminacion = conexionEscritura
            .delete(
                "PIEZA", // Nombre tabla
                "idPieza=? and disp=?", // Consulta Where
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarPiezaFormulario(
        disp: Int,
        nombrePieza: String,
        peso: Float,
        garantia: Boolean,
        fabricante: String,
        idPieza:Int,
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombrePieza", nombrePieza)
        //valoresAActualizar.put("disp", disp)
        valoresAActualizar.put("peso", peso)
        valoresAActualizar.put("garantia", garantia)
        valoresAActualizar.put("fabricante", fabricante)
        // where ID = ?
        val parametrosConsultaActualizar = arrayOf( idPieza.toString(), disp.toString() )
        val resultadoActualizacion = conexionEscritura
            .update(
                "PIEZA", // Nombre tabla
                valoresAActualizar, // Valores
                "idPieza=? and disp=?", // Consulta Where
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if(resultadoActualizacion.toInt() == -1) false else true
    }



    fun consultarPiezaPorID(idPieza: Int, disp: Int): Pieza{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM PIEZA WHERE idPieza = ? AND disp = ?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(idPieza.toString(), disp.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura,
        )

        // logica busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = Pieza(0, 0 , "",0.0f,false,"")
        val arreglo = arrayListOf<Pieza>()
        if(existeUsuario){
            do{
                var idPieza= resultadoConsultaLectura.getInt(0) // Indice 0
                val disp = resultadoConsultaLectura.getString(1)
                val nombrePieza= resultadoConsultaLectura.getString(2)
                val peso= resultadoConsultaLectura.getString(3)
                val garantia= resultadoConsultaLectura.getString(4)
                val fabricante= resultadoConsultaLectura.getString(5)

                if(idPieza != null){
                    // llenar el arreglo con un nuevo BEntrenador
                    usuarioEncontrado.idPieza= idPieza
                    usuarioEncontrado.disp = disp.toInt()
                    usuarioEncontrado.nombrePieza = nombrePieza
                    usuarioEncontrado.peso = peso.toFloat()
                    usuarioEncontrado.garantia = garantia.toBoolean()
                    usuarioEncontrado.fabricante = fabricante
                }
            } while (resultadoConsultaLectura.moveToNext())
        }


        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }

    fun consultarPiezasPorForanea(foraneaValue: Int): ArrayList<Pieza> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
        SELECT * FROM PIEZA WHERE disp = ?
    """.trimIndent()
        val parametrosConsultaLectura = arrayOf(foraneaValue.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura,
        )

        val piezaList = arrayListOf<Pieza>()

        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val idPieza = resultadoConsultaLectura.getInt(0)
                val disp = resultadoConsultaLectura.getInt(1)
                val nombrePieza = resultadoConsultaLectura.getString(2)
                val peso = resultadoConsultaLectura.getFloat(3)
                val garantia = resultadoConsultaLectura.getInt(4) != 0
                val fabricante = resultadoConsultaLectura.getString(5)

                //val pieza = Pieza(idPieza,disp,nombrePieza,peso,garantia,fabricante)
               // piezaList.add(pieza)

                if(idPieza != null){
                    val pieza = Pieza(1, 2, "tornillo", 2f,true, "tornix")
                    pieza.idPieza = idPieza
                    pieza.disp = disp
                    pieza.nombrePieza = nombrePieza
                    pieza.peso = peso
                    pieza.garantia = garantia
                    pieza.fabricante = fabricante

                    piezaList.add(pieza)
                }
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return piezaList
    }




}