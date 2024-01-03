import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import java.util.Date

fun main() {
    val scanner = Scanner(System.`in`)

    while (true) {
        println("***************Bienvenido*************")
        println("MENU DE OPCIONES:")
        println("1. Dispositivos")
        println("2. Piezas")
        println("0. Salir")

        println("Ingresa una opcion: ")
        val opcionM = scanner.nextInt()

        when (opcionM) {
            1 -> {
                println("MENU DE OPCIONES PARA MANEJO DE DISPOSITIVOS:")
                println("1. Crear Dispositivo")
                println("2. Listar Dispositivos")
                println("3. Actualizar Dispositivo")
                println("4. Eliminar Dispositivo")
                println("5. Salir")

                println("Ingresa una opcion: ")
                val opcionMD = scanner.nextInt()

                when (opcionMD) {
                    1 -> {
                        println("****INGRESAR DISPOSITIVO****")
                        print("Ingrese el ID del dispositivo: ")
                        val idDispositivo = scanner.nextInt()
                        print("Ingrese el nombre del dispositivo: ")
                        val nombreDispositivo = readLine()
                        print("Ingrese la fecha de creación (dd/MM/yyyy): ")
                        val fechaCreacion = scanner.next()
                        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
                        val fechaFormateada = formatoFecha.parse(fechaCreacion)
                        print("Ingrese el estado de stock (true/false): ")
                        val stock = scanner.nextBoolean()
                        print("Ingrese el precio del dispositivo: ")
                        val precioDispositivo = scanner.nextDouble()

                        val piezas = mutableListOf<Piezas>()

                        while (true) {
                            println("Ingresar una pieza (s/n)")
                            val op = scanner.next()
                            if (op.equals("s", ignoreCase = true)) {
                                val piezasActuales = Piezas.leerPiezas()
                                piezasActuales.forEach { println( it.toString())}
                                println("Ingrese el ID de la pieza a ingresar:")
                                val idPieza = scanner.nextInt()
                                val nuevaPieza = Piezas.leerPiezas().find { it.idPieza == idPieza }
                                if (nuevaPieza != null) {
                                    piezas.add(nuevaPieza)
                                    println("Pieza agregada con exito!")
                                } else {
                                    println("Pieza con el el ID $idPieza no encontrada.")
                                }
                            } else {
                                break
                            }
                        }
                        val nuevoDispositivo = Dispositivo(
                            idDispositivo = idDispositivo,
                            nombreDispositivo = nombreDispositivo,
                            fechaCreacion = fechaFormateada,
                            stock = stock,
                            precio = precioDispositivo,
                            piezas.toTypedArray()
                        )

                        Dispositivo.crearDispositivo(nuevoDispositivo)
                        println("Carga de dispositivo exitosa!")

                    }

                    2 -> {
                        val listaDispositivos = Dispositivo.leerDispositivos()
                        if (listaDispositivos.isNotEmpty()) {
                            println("****LISTA DE DISPOSITIVOS****")
                            listaDispositivos.forEach { println( it.toString())}
                        } else {
                            println("No hay dispositivos por mostrar")
                        }
                    }

                    3 -> {
                        println("****ACTUALIZAR DISPOSITIVO****")
                        val listaDispositivosActuales = Dispositivo.leerDispositivos()
                        println("Dispositivos actuales")
                        listaDispositivosActuales.forEach { println(it) }

                        println("Ingrese el ID del dispositivo que desea actualizar: ")
                        val id = scanner.nextInt()
                        val dispActual = listaDispositivosActuales.find { it.idDispositivo == id }

                        if (dispActual != null) {
                            do {
                                println(
                                    "Ingrese el atributo que desea actualizar:/n" +
                                            "1.Nombre/n" +
                                            "2.Fecha/n" +
                                            "3.Stock/n" +
                                            "4.Precio" +
                                            "5.Piezas"
                                )
                                val atributo = scanner.nextInt()

                                when (atributo) {
                                    1 -> {
                                        println("Nuevo Nombre:")
                                        dispActual.nombreDispositivo = readLine()
                                    }

                                    2 -> {
                                        println("Nueva Fecha:")
                                        val fechaCreacion = scanner.next()
                                        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
                                        val fechaFormateada = formatoFecha.parse(fechaCreacion)
                                        dispActual.fechaCreacion = fechaFormateada
                                    }

                                    3 -> {
                                        println("Nuevo Stock:")
                                        dispActual.stock = scanner.nextBoolean()
                                    }

                                    4 -> {
                                        println("Nuevo Precio:")
                                        dispActual.precio = scanner.nextDouble()
                                    }

                                    5 -> {
                                        println("Cantidad de piezas:")
                                        val cantidadPiezas = scanner.nextInt()
                                        val piezasActuales = Piezas.leerPiezas()
                                        val piezasSeleccionadas = mutableListOf<Piezas>()

                                        repeat(cantidadPiezas){
                                            println("Receta ${it + 1}:")
                                            val idPieza = scanner.nextInt()
                                            val pieza = piezasActuales.find { it.idPieza == idPieza }
                                            if(pieza != null){
                                                piezasSeleccionadas.add(pieza)
                                            }
                                        }
                                        dispActual.piezas = piezasSeleccionadas.toTypedArray()
                                    }
                                    else -> {
                                        println("Atributo no válido.")
                                        break
                                    }
                                }

                                println("¿Desea actualizar otro atributo? (s/n):")
                            } while (readLine()?.toLowerCase() == "s")
                            Dispositivo.actualizarDispositivo(dispActual)
                            println("Dipositivo actualizado con éxtio!")

                        } else {
                            println("Dispositivo no encontrado")
                        }

                        val listaDispositivosActualizada = Dispositivo.leerDispositivos()
                        println("\nLista de dispositivos después de la actualización:")
                        listaDispositivosActualizada.forEach { println(it) }

                    }

                    4 -> {
                        println("****ELIMINAR DISPOSITIVO****")
                        val listaDispositivosActuales = Dispositivo.leerDispositivos()
                        println("Dispositivos actuales")
                        listaDispositivosActuales.forEach { println(it) }
                        println("Ingrese el ID del dispositivo que desea eliminar: ")
                        val id = scanner.nextInt()
                        Dispositivo.eliminarDispositivo(id)
                        println("Dipositivo eliminado con éxtio!")

                    }


                    else -> {
                        println("Opcion Invalida")
                    }

                }


            }

            2 -> {
                println("MENU DE OPCIONES PARA MANEJO DE PIEZAS:")
                println("1. Crear Pieza")
                println("2. Listar Piezas")
                println("3. Actualizar Pieza")
                println("4. Eliminar Pieza")

                println("Ingresa una opcion: ")
                val opcionMP = scanner.nextInt()

                when (opcionMP) {
                    1 -> {
                        println("****INGRESAR PIEZA****")
                        print("Ingrese el ID de la pieza: ")
                        val idPieza = scanner.nextInt()
                        print("Ingrese el nombre de la pieza: ")
                        val nombrePieza = readLine()
                        print("Ingrese el peso de la pieza: ")
                        val peso = scanner.nextDouble()
                        print("Ingrese si tiene garantia (true/false): ")
                        val garantia = scanner.nextBoolean()
                        print("Ingrese el fabricante de la pieza: ")
                        val fabricante = readLine()

                        val nuevaPieza = Piezas(
                            idPieza = idPieza,
                            nombrePieza = nombrePieza,
                            peso = peso,
                            garantia = garantia,
                            fabricante = fabricante
                        )

                        Piezas.crearPieza(nuevaPieza)
                        println("Carga de pieza exitosa!")
                    }

                    2 -> {
                        val listaPiezas = Piezas.leerPiezas()
                        if (listaPiezas.isNotEmpty()) {
                            println("****LISTA DE PIEZAS****")
                            listaPiezas.forEach { println(it.toString()) }
                        } else {
                            println("No hay piezas por mostrar")
                        }
                    }

                    3 -> {
                        println("****ACTUALIZAR PIEZA****")
                        val listaPiezasActuales = Piezas.leerPiezas()
                        println("Piezas actuales")
                        listaPiezasActuales.forEach { println(it) }

                        println("Ingrese el ID de la Pieza que desea actualizar: ")
                        val id = scanner.nextInt()

                        val piezaActual = listaPiezasActuales.find { it.idPieza == id }

                        if (piezaActual != null) {
                            do {
                                println( "Ingrese el atributo que desea actualizar:" )
                                println("1. Nombre")
                                println("2. Peso")
                                println("3. Garantia")
                                println("4. Fabricante")
                                val atributo = scanner.nextInt()

                                when (atributo) {
                                    1 -> {
                                        println("Nuevo Nombre:")
                                        piezaActual.nombrePieza = readLine()
                                    }

                                    2 -> {
                                        println("Nuevo Peso:")
                                        piezaActual.peso = scanner.nextDouble()
                                    }

                                    3 -> {
                                        println("Nueva Garantia:")
                                        piezaActual.garantia = scanner.nextBoolean()
                                    }

                                    4 -> {
                                        println("Nuevo Fabricante:")
                                        piezaActual.fabricante = readLine()
                                    }

                                    else -> {
                                        println("Atributo no válido.")
                                        break
                                    }
                                }

                                println("¿Desea actualizar otro atributo? (s/n):")
                            } while (readLine()?.toLowerCase() == "s")
                            Piezas.actualizarPieza(piezaActual)
                            println("Pieza actualizada con éxtio!")

                        } else {
                            println("Pieza no encontrada")
                        }

                        val listaPiezasActualizada = Piezas.leerPiezas()
                        println("\nLista de piezas después de la actualización:")
                        listaPiezasActualizada.forEach { println(it) }

                    }

                    4 -> {
                        println("****ELIMINAR PIEZA****")
                        val listaPiezasActuales = Piezas.leerPiezas()
                        println("Piezas actuales")
                        listaPiezasActuales.forEach { println(it) }
                        println("Ingrese el ID de la pieza que desea eliminar: ")
                        val id = scanner.nextInt()
                        Piezas.eliminarPieza(id)
                        println("Pieza eliminada con éxtio!")

                    }

                    else -> {
                        println("Opcion Invalida")
                    }

                }

            }
            0 -> {
                println("Saliendo...")
                return
            }
            else -> {
                println("Opcion invalida.")
            }
        }
    }

}
