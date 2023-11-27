package models

import java.lang.StringBuilder

class Sala(
   val filas: Int = 3,
   val columnas: Int = 3,
   val butacas: Array<Array<Butaca>> = Array(filas) {Array(columnas) { Butaca.random()} }
) {
    override fun toString(): String {
        val result = StringBuilder()
        for(filas in butacas){
            result.append("[" + getLetraFila(butacas.indexOf(filas)) + "]")
            for(butaca in filas){
                result.append(butaca)
            }
            result.append("\n")
        }
        return result.toString()
    }

    private fun getLetraFila(filas: Int) = (65 + filas).toChar()

    fun isVacia(): Boolean{
        for (filas in butacas.indices){
            for (columnas in butacas[filas].indices){
                if(!butacas[filas][columnas].ocupada){
                    return false
                }
            }
        }
        return true
    }

    fun isLlena(): Boolean{
        val total = filas * columnas
        var ocupadas = 0
        for (filas in butacas.indices){
            for(columnas in butacas[filas].indices){
                if(butacas[filas][columnas].ocupada){
                    ocupadas++
                }
            }
        }
        return total == ocupadas
    }
}