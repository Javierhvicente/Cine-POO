import models.Configuración
import models.Sala
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    println("Bienvenido al cine")
    val config = validarArgs(args)
    val sala = Sala(config.filas, config.columnas)
    imprimirSala(sala)
    var opcion: Int
    do{
        println("1 ver sala")
        println("2 comprar entrada")
        println("3 devolver entrada")
        println("4 ver recaudación")
        println("5 Salir")
        opcion = readln().toIntOrNull() ?: -1
        when(opcion){
            1 -> verSala()
            2 -> comprarEntrada(sala)
            3 -> devolverEntrada()
            4 -> verRecaudacion()
            5 -> despedirse()
            else -> println("Opción no válida")
        }
    }while (opcion != 5)
}

fun despedirse() {
    println("Hasta luego, vuelva pronto")
}

fun verRecaudacion() {
    TODO("Not yet implemented")
}

fun devolverEntrada() {
    TODO("Not yet implemented")
}

fun comprarEntrada(sala: Sala) {
    if(sala.isLlena()){
        println("La sala está llena, no se pueden comprar más entradas")
        return
    }
    val asiento = getAsiento(sala, false)
}

fun inputAsiento():String {
    println("Indque el asiento que desee con el siguiente formato: A:1 , B:4, C:3")
    val regexInput = Regex("[A-Z]:[1-9]")
    var input = ""
    var correcto = true
    do{
        input = readln().trim().uppercase()
        if(!regexInput.matches(input)){
            println("El formato no es válido, debe introducir Letra:Númer, Ejemplo: B:2")
            correcto = false
        }
    }while (!correcto)
    return input
}

fun validatePosicion(sala: Sala, fila: String, columna: Int): Boolean {
    TODO("Not yet implemented")
}

fun getAsiento(sala: Sala, isOcupada: Boolean = false): IntArray {
    var correcto = true
    var fila = ""
    var columna = ""
    var filaInt = 0
    var columnaInt = 0
    do{
        val asiento = inputAsiento()
        fila = asiento.split(":")[0].uppercase()
        columna = asiento.split(":")[1].toIntOrNull()
        correcto = validatePosicion(sala, fila, columna)
    }while (!correcto)
}


fun verSala() {
    TODO("Not yet implemented")
}

private fun imprimirSala(sala: Sala) {
    println(sala)
}

private fun validarArgs(args: Array<String>): Configuración {
    if(args.size != 2){
        mensajeErrorValidacion()
    }
    val filas = args[0]
    val columnas = args[1]
    if(!filas.lowercase().startsWith("Filas:") || !columnas.lowercase().startsWith("Columnas:")){
        mensajeErrorValidacion()
    }
    val filasInt = filas.substringAfter(":").toIntOrNull() ?: 0
    val columnasInt = columnas.substringAfter(":").toIntOrNull() ?: 0
    if(filasInt !in 3..5 || columnasInt !in 3..5){
        mensajeErrorValidacion()
    }
    return Configuración(filasInt, columnasInt)

}

fun mensajeErrorValidacion() {
    println("Error: Argumentos inválidos: No ha pasado los argumentos correctos o no son válidos")
    println("Ejemplo: Java -jar cine.jar filas:3 columnas:3")
    println("Filas entre 3 y 5")
    println("Columnas entre 3 y 5")
    exitProcess(0)
}
