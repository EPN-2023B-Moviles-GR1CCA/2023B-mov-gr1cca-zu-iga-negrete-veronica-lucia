import java.util.*

fun main(){
    println("Hola mundo")
    //INMUTABLE (No se reasigna "=")
    val inmutable: String = "Vero";
    // inmutable = "Lucia";

    //MUTABLES (Re asignar)
    var mutable: String = "Veronica";

    //val > var , preferible usar val
    //Duck Typing, no es necesario definir
    var ejemploVariable = "Veronica Lucia"
    val edadEjemplo: Int = 12
    ejemploVariable.trim()


    //Variable primitiva
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true


    // Clase JAVA
    val fechaNacimiento: Date = Date()


    //SWITCH, no existen como tal
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }

    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"
}
//void -> Unit (no devuelve nada)

//FUNCIONES
// fun nombreFuncion(parametros) se puede poner o no lo que devuelve la funcion

fun imprimirNombre(nombre: String): Unit{
    // en lugar de concatenar
    println("Nombre : ${nombre}")
}
//el lenguaje avisa al programador que un valor puede ser nulo (null pointer exception)
fun calcularSueldo(
    sueldo:Double, //Requerido
    tasa:Double = 12.00,  //Opcional (defecto)
    bonoEspecial:Double? = null //Opcion de null -> nullable (se lo puede hacer a cualquier tipo de variable)
): Double{
    //Int -> Int?
    //String -> String?
    //Date -> Date? y sera necesario usar los if para tratarlos
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        bonoEspecial.dec()
        return sueldo * (100/tasa) + bonoEspecial
    }
}