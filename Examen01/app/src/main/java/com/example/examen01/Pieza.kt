package com.example.examen01

class Pieza(
    var idPieza: Int ,
    var disp: Int,
    var nombrePieza: String?,
    var peso: Float?,
    var garantia: Boolean?,
    var fabricante: String?
) {
    override fun toString(): String {
        return "ID #$idPieza \nNombre: $nombrePieza \nGarantia: $garantia \nPeso: $peso \nFabricante: $fabricante"
    }
}