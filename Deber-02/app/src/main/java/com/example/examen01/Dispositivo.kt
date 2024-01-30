package com.example.examen01

import java.text.SimpleDateFormat
import java.util.Date

 class Dispositivo(
    var idDispositivo: Int,
    var nombreDispositivo: String?,
    var fechaCreacion: String?,
    var stock: Boolean,
    var precio: Float?,
) {
    override fun toString(): String {
        return "ID #$idDispositivo \nNombre: $nombreDispositivo \nFecha creación: $fechaCreacion \nStock: $stock\nPrecio: $precio"
    }
}