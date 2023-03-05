package com.ashish.royalmobileadminapp.data.product

class ColorObject(var nm:String , var hex:String , var contraHex : String)
{
    val haxHash : String = "#$hex"
    val contrastHex  : String = "#$contraHex"
}