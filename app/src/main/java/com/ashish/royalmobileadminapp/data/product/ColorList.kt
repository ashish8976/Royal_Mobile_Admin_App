package com.ashish.royalmobileadminapp.data.product

class ColorList
{
    private val blackHex = "000000"
    private val whiteHex = "FFFFFF"

    val defaultColor: ColorObject = basicColors()[0]

    fun colorPosition(colorObject: ColorObject): Int
    {
        for (i in basicColors().indices)
        {
            if(colorObject == basicColors()[i])
                return i
        }
        return 0
    }

    fun basicColors(): List<ColorObject>
    {
        return listOf(
            ColorObject("Black", blackHex, whiteHex),
            ColorObject("Silver", "C0C0C0", blackHex),
            ColorObject("Gray", "808080", whiteHex),
            ColorObject("Maroon", "800000", whiteHex),
            ColorObject("Red", "FF0000", whiteHex),
            ColorObject("Fuchsia", "FF00FF", whiteHex),
            ColorObject("Green", "008000", whiteHex),
            ColorObject("Lime", "00FF00", blackHex),
            ColorObject("Olive", "808000", whiteHex),
            ColorObject("Yellow", "FFFF00", blackHex),
            ColorObject("Navy", "000080", whiteHex),
            ColorObject("Blue", "0000FF", whiteHex),
            ColorObject("Teal", "008080", whiteHex),
            ColorObject("Aqua", "00FFFF", blackHex)
        )
    }
}

//private val blackHax = "000000"
//private val whiteHax = "FFFFFF"
//
//val defaultColor : ColorObject = basicColor()[0]
//
//fun colorPosition(colorObject: ColorObject) : Int
//{
//    for (i in basicColor().indices)
//    {
//        if (colorObject == basicColor()[i])
//        {
//            return 1
//        }
//    }
//    return 0
//}
//
//fun basicColor() : List<ColorObject>
//{
//    return listOf(
//        ColorObject("Black",blackHax,whiteHax),
//        ColorObject("Silver","C0C0C0",blackHax),
//        ColorObject("Gray","808080",whiteHax),
//        ColorObject("Maroon","800000",whiteHax),
//        ColorObject("Red","FF0000",whiteHax),
//        ColorObject("Fuchsia","FF00FF",whiteHax),
//        ColorObject("Green","008000",whiteHax),
//        ColorObject("Lime","00FF00",blackHax),
//        ColorObject("Yellow","FFFF00",blackHax),
//        ColorObject("Blue","0000FF",whiteHax),
//        ColorObject("Teal","008080",whiteHax)
//    )
//}