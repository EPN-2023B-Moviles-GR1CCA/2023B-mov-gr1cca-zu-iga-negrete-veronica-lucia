import com.google.gson.Gson
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date

class Piezas(
    var idPieza: Int ,
    var nombrePieza: String?,
    var cantidad: Int?,
    var peso: Double?,
    var idDispositivo: Dispositivo
) {
    companion object{
        private const val PIEZAS_FILE = "C:/Github/2023B-mov-gr1cca-zuniga-negrete-veronica-lucia/Deber-01/DispositivoPiezas/src/main/kotlin/piezas.txt"
        private val gson = Gson()
        private val piezasFile = File(PIEZAS_FILE)
        fun crearPieza(nuevaPieza:  Piezas){
            val listaPiezas = leerPiezas().toMutableList()
            listaPiezas.add(nuevaPieza)
            guardarPiezaArchivo(listaPiezas)
        }

        fun leerPiezas(): List<Piezas> {
            return piezasFile.useLines { lines ->
                lines.map { gson.fromJson(it, Piezas::class.java) }
                    .toList()
            }
        }
        fun guardarPiezaArchivo(piezas: List<Piezas>){
            BufferedWriter(FileWriter(piezasFile)).use { writer ->
                piezas.forEach { pieza ->
                    val jsonPieza = gson.toJson(pieza)
                    writer.write(jsonPieza)
                    writer.newLine() // Agregar un salto de línea después de cada receta
                }
            }
        }

        fun actualizarPieza(piezaAc: Piezas){
            val listaPiezas = leerPiezas().toMutableList()

            val listaPieActualizada = listaPiezas.map {
                if (it.idPieza == piezaAc.idPieza) piezaAc else it
            }

            Piezas.guardarPiezaArchivo(listaPieActualizada)
        }
        fun eliminarPieza(id: Int) {
            val piezas = leerPiezas().toMutableList()

            val piezaAEliminar = piezas.find { it.idPieza == id }

            if (piezaAEliminar != null) {
                piezas.remove(piezaAEliminar)
                guardarPiezaArchivo(piezas)
            } else {
                println("No se encontró un dispositivo con el ID $id.")
            }
        }
    }
    override fun toString(): String {
        return "ID #$idPieza \nNombre: $nombrePieza \nCantidad: $cantidad \nPeso: $peso"
    }
}