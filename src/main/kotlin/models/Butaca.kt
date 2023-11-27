package models
const val PROB_NORMAL = 60
const val PROB_VIP = 10
const val PROB_ESPECIAL = 30

class Butaca private constructor(
    var ocupada: Boolean = false,
    val tipo: TipoButaca = TipoButaca.NORMAL
) {
    companion object{
        fun default(): Butaca{
            return Butaca()
        }
        fun vip():Butaca{
            return Butaca(tipo = TipoButaca.VIP)
        }
        fun especial():Butaca{
            return Butaca(tipo = TipoButaca.ESPECIAL)
        }


        fun random(): Butaca{
            val random = (0..100).random()
            return when{
                random <= PROB_ESPECIAL -> Butaca (tipo = TipoButaca.ESPECIAL )
                random <= PROB_VIP -> Butaca(tipo = TipoButaca.VIP)
                else -> Butaca()
            }
        }
    }
    override fun toString():String{
        return when(ocupada){
            true -> "[🔴]"
            false ->when(tipo){
                TipoButaca.NORMAL -> "[🟢]"
                TipoButaca.ESPECIAL -> "[🔵]"
                TipoButaca.VIP -> "[🟣]"
            }
        }
    }

}