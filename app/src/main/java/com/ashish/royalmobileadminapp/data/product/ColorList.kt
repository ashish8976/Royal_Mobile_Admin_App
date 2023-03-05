package com.ashish.royalmobileadminapp.data.product

class ColorList
{
    private val blackHax = "000000"
    private val whiteHax = "FFFFFF"

    val defaultColor : ColorObject = basicColor()[0]

    fun colorPosition(colorObject: ColorObject) : Int
    {
        for (i in basicColor().indices)
        {
            if (colorObject == basicColor()[i])
            {
                return 1
            }
        }
        return 0
    }

    fun basicColor() : List<ColorObject>
    {
        return listOf(
                ColorObject("Black",blackHax,whiteHax),
            ColorObject("Silver","C0C0C0",blackHax),
            ColorObject("Gray","808080",whiteHax),
            ColorObject("Maroon","800000",whiteHax),
            ColorObject("Red","FF0000",whiteHax),
            ColorObject("Fuchsia","FF00FF",whiteHax),
            ColorObject("Green","008000",whiteHax),
            ColorObject("Lime","00FF00",blackHax),
            ColorObject("Yellow","FFFF00",blackHax),
            ColorObject("Blue","0000FF",whiteHax),
            ColorObject("Teal","008080",whiteHax)
        )
    }
}