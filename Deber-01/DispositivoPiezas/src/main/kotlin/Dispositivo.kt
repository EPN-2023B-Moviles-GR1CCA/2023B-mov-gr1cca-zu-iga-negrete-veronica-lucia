import java.io.File
import java.util.Date
import com.google.gson.Gson
import java.io.BufferedWriter
import java.io.FileWriter
import java.text.SimpleDateFormat

class Dispositivo(
    var idDispositivo: Int ,
    var nombreDispositivo: String?,
    var fechaCreacion: Date,
    var stock: Boolean,
    var precio: Double?,
    var piezas: Array<Piezas>
) {

    companion object{
        private const val DISPOSITIVO_FILE = "C:/Github/2023B-mov-gr1cca-zuniga-negrete-veronica-lucia/Deber-01/DispositivoPiezas/src/main/kotlin/dispositivo.txt"
        private val gson = Gson()
        private val dispositivoFile = File(DISPOSITIVO_FILE)
        fun crearDispositivo(nuevoDispositivo:  Dispositivo){
           val listaDispositivos = leerDispositivos().toMutableList()
           listaDispositivos.add(nuevoDispositivo)
            guardarDispositivoArchivo(listaDispositivos)
        }

        fun leerDispositivos(): List<Dispositivo> {
            return dispositivoFile.useLines { lines ->
                lines.map { gson.fromJson(it, Dispositivo::class.java) }
                    .toList()
            }
        }
        fun guardarDispositivoArchivo(dispositivos: List<Dispositivo>){
            BufferedWriter(FileWriter(dispositivoFile)).use { writer ->
                dispositivos.forEach { dispositivo ->
                    val jsonDispo = gson.toJson(dispositivo)
                    writer.write(jsonDispo)
                    writer.newLine() // Agregar un salto de línea después de cada receta
                }
            }
        }

        fun actualizarDispositivo(dispositivoAc: Dispositivo){
            val listaDispositivos = leerDispositivos().toMutableList()

            val listaDipActualizada = listaDispositivos.map {
                if (it.idDispositivo == dispositivoAc.idDispositivo) dispositivoAc else it
            }

            Dispositivo.guardarDispositivoArchivo(listaDipActualizada)
        }
        fun eliminarDispositivo(id: Int) {
            val dispositivos = leerDispositivos().toMutableList()

            val dispositivoAEliminar = dispositivos.find { it.idDispositivo == id }

            if (dispositivoAEliminar != null) {
                dispositivos.remove(dispositivoAEliminar)
                guardarDispositivoArchivo(dispositivos)
            } else {
                println("No se encontró un dispositivo con el ID $id.")
            }
        }
    }
    override fun toString(): String {
        val formato = SimpleDateFormat("ddd-MM-yyyy")
        val fecha = formato.format(fechaCreacion)
        return "ID #$idDispositivo \nNombre: $nombreDispositivo \nFecha: $fecha \nStock: $stock\nPrecio: $precio\nPiezas del dispositivo: ${piezas.contentToString()}\n"
    }
}