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

    // Paso de parametross
    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00)
    calcularSueldo(10.00, 12.00, 20.00)

    //NOMBRADOS
    calcularSueldo(sueldo = 10.00)
    calcularSueldo(sueldo = 10.00, tasa = 15.00)
    calcularSueldo(sueldo = 10.00, tasa = 15.00, bonoEspecial = 20.00)

    calcularSueldo(sueldo = 10.00, bonoEspecial = 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)   // no es necesario el orden


    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)





}

//CLASES

//COMO ES EN JAVA
abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(uno: Int, dos: Int){ //BLOQUE DEL CODIGO DEL CONSTRUCTOR
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}


//COMO ES EN KOTLIN
//se define dentro con un contructor primario, dentro el

abstract class Numeros( //CONSTRUCTOR PRIMARIO
    //Ejemplo:
    //unoProp: Int // (Parametro (sin modificador de acceso))
    //private var uno: Int // (Propiedad de la clase)
    //var uno: Int, // Propiedad de la clase (por defecto es PUBLIC)
    //public var uno: Int,

    //Propiedad de la clase protected numeros.numeroUno
    protected val numeroUno: Int,
    //Propiedad de la clase protected numeros.numeroDos
    protected val numeroDos: Int
){
    // var cedula: string = "" (public es por defecto)
    // private valorCalculado: Int = 0 (private)
    init { //BLOQUE DE CODIFGO CONSTRUCTOR PRIMARIO
        this.numeroUno; this.numeroDos; //this es opcional
        numeroUno; numeroDos;           // sin el this, es lo mismo
        println("Inicializando")
    }
}

class Suma( // Constructor Primario Suma
    uno: Int, // Parametro
    dos: Int // Parametro
): Numeros(uno, dos) { // <- Constructor del Padre
    init { // Bloque constructor primario
        this.numeroUno; numeroUno;
        this.numeroDos; numeroDos;
    }

    constructor(//  Segundo constructor
        uno: Int?, // parametros
        dos: Int // parametros
    ) : this(  // llamada constructor primario
        if (uno == null) 0 else uno,
        dos
    ) { // si necesitamos bloque de codigo lo usamos
        numeroUno;
    }

    constructor(//  tercer constructor
        uno: Int, // parametros
        dos: Int? // parametros
    ) : this(  // llamada constructor primario
        uno,
        if (dos == null) 0 else uno
    )
    // Si no lo necesitamos al bloque de codigo "{}" lo omitimos

}

////////////////////////////////////////////////////////////////////////////////////////////////////////

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