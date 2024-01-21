package com.example.examen01

import java.text.SimpleDateFormat
import java.util.Date

class BaseDatosDispositivo {
    companion object{
        val arregloBDispositivo = arrayListOf<Dispositivo>()
        init {
            arregloBDispositivo.add(Dispositivo(1,"Celular","12/01/2021",true,12.5f))
        }


    }
}