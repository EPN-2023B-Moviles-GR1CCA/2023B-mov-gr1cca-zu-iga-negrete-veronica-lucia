package com.example.examen01

import java.text.SimpleDateFormat
import java.util.Date

data class Dispositivo(
    var idDispositivo: Int,
    var nombreDispositivo: String?,
    var fechaCreacion: Date,
    var stock: Boolean,
    var precio: Double?
) {
    override fun toString(): String {
        val formato = SimpleDateFormat("ddd-MM-yyyy")
        val fecha = formato.format(fechaCreacion)
        return "ID #$idDispositivo \nNombre: $nombreDispositivo \nFecha creación: $fecha \nStock: $stock\nPrecio: $precio"
    }
}