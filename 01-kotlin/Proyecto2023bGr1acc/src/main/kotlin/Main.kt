import java.util.*
import kotlin.collections.ArrayList

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
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    //se accede sin necesidad de crear una instancia del companion objetc
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //////////////////////////////////////////////////////////////////////////////////
    //ARREGLOS


    //TIPOS DE ARREGLOS

    //ARREGLOS ESTATICOS
    val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)
    println(arregloEstatico)

    //ARREGLOS DINAMICOS
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)

    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)


    //FOR EACH -> UNIT = Iterar un arreglo
    val repuestaForEach: Unit = arregloDinamico
        .forEach {valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
    // IT (ESO) SIGNIFICA EL ELEMENTO ITERADO
    arregloDinamico.forEach{println(it)}

    arregloEstatico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(repuestaForEach)

    //MAP -> muta el arreglo (cambia el arreglo )
    // 1) enviemos el nuevo valor de la iteracion
    // 2) nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map{valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it + 15 }

    //FILTER -> Filtrar el arreglo
    // 1) devolver una expresion (true o false)
    // 2) nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            //expresion condicion
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }

    val respuestaFilerDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilerDos)


    // OR AND
    // AND ->  ALL (Todos cumplen?)
    // OR ->  ANY (Alguno cumple?)
    // V AND V -> V / V AND F -> F
    // V OR V -> V / V OR F -> V / F OR F ->F
    val respuestaAny: Boolean = arregloDinamico
        .any{valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) //true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll)

    // REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5
    val respuestaReduce: Int = arregloDinamico
        .reduce{ // acumulado = 0 -> SIEMPRE EMPIEZA EN 0
            acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual)// logica negocio
        }
    println(respuestaReduce) //78




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

    constructor( //cuarto constructor
        uno: Int?,
        dos: Int?
    ):this(
        if(uno == null) 0 else uno,
        if(dos == null) 0 else uno
    )

    // public por defecto, o usar private o protected
    public fun sumar(): Int{
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }
  

    companion object{ //Atributos y Metodos "Compartidos" entre las instancias
        val pi = 3.14

        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }
        val historialSumas = arrayListOf<Int>()

        fun agregarHistorial(valorNuevaSuma:Int){
            historialSumas.add(valorNuevaSuma)
        }
    }





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