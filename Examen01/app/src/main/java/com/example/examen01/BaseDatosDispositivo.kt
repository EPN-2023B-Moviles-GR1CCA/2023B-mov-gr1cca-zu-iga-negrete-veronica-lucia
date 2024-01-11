package com.example.examen01

import java.text.SimpleDateFormat
import java.util.Date

class BaseDatosDispositivo {
    companion object{
        val arregloBDispositivo = arrayListOf<Dispositivo>()
        init {
            val fechaCreacion: Date = Date("12/01/2021")
            arregloBDispositivo.add(Dispositivo(1,"Celular",fechaCreacion,true,12.5))

        }


    }
}