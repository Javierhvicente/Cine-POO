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
            1 -> verSala(sala)
            2 -> comprarEntrada(sala)
            3 -> devolverEntrada(sala)
            4 -> verRecaudacion(sala)
            5 -> despedirse()
            else -> println("Opción no válida")
        }
    }while (opcion != 5)
}

fun despedirse() {
    println("Hasta luego, vuelva pronto")
}

fun verRecaudacion(sala: Sala) {
    TODO("Not yet implemented")
}

fun devolverEntrada(sala: Sala) {
    if(sala.isVacia()){
        println("La sala está vacía, no se puede devolver ninguna entrada")
        return
    }
    val asiento = getAsiento(sala, true)
    sala.actualizarButaca(asiento[0], asiento[1], false)
    val fila: Char = (asiento[0] + 65).toChar()
    println("Butaca devuelta: $fila:${asiento[1]+1} ha sido devuelta")

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
    if((columna-1) !in 0..sala.columnas){
        println("La columna no es válida, debe ser entre 1 y ${sala.columnas}")
        return false
    }
    if(fila[0].code !in 65..(65 + sala.filas)){
        println("LA filas no es válida, debe ser una letra entre A y ${sala.filas + 64}")
        return false
    }
    return true
}

fun getAsiento(sala: Sala, isOcupada: Boolean = false): IntArray {
    var correcto = true
    var fila = ""
    var columna = 0
    var filaInt = 0
    var columnaInt = 0
    do{
        val asiento = inputAsiento()
        fila = asiento.split(":")[0].uppercase()
        columna = asiento.split(":")[1].toIntOrNull() ?: 0
        correcto = validatePosicion(sala, fila, columna)
        filaInt = fila[0].code - 65
        columnaInt = columna - 1
        correcto = isAsientoOcupado(sala, filaInt, columnaInt, isOcupada)
    }while (!correcto)
    return intArrayOf(filaInt, columnaInt)
}

fun isAsientoOcupado(sala: Sala, filaInt: Int, columnaInt: Int, ocupada: Boolean): Boolean {
    if(sala.isOcupada(filaInt, columnaInt) == !ocupada){
        println("La butaca no está disponible o ya ha sido reservada por otro cliente, por favor elige otra butaca")
        return false
    }
    return true
}


fun verSala(sala: Sala) {
    println(sala)
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
