package com.ashish.royalmobileadminapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.ashish.royalmobileadminapp.R
import com.ashish.royalmobileadminapp.data.product.ColorObject

class ColorSpinnerAdapter(context: Context,list: List<ColorObject>)
    : ArrayAdapter<ColorObject>(context,0,list)
{
    private var layoutInflater = LayoutInflater.from(context)

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view : View = layoutInflater.inflate(R.layout.color_spinner_bg,null,true)

        return view(view,position)

    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

            var cv = convertView
            if (cv == null)
                cv = layoutInflater.inflate(R.layout.color_spineer_item,parent,false)
            return  view(cv!!,position)
    }

    private fun view(view: View, position: Int): View {

        val colorObject : ColorObject = getItem(position) ?: return view

        val colorNameItem = view.findViewById<TextView>(R.id.colorName)
        val colorHexItem = view.findViewById<TextView>(R.id.colorHexa)
        val colorNameBg = view.findViewById<TextView>(R.id.colorNameBg)
        val view = view.findViewById<View>(R.id.colorBlob)
        val colorBlob = view as? TextView


        colorNameBg?.text = colorObject.nm
        colorNameBg?.setTextColor(android.graphics.Color.parseColor(colorObject.contrastHex))
        colorNameItem?.text = colorObject.nm
        colorHexItem?.text = colorObject.hex

        colorBlob?.background?.setTint(android.graphics.Color.parseColor(colorObject.haxHash))

        return view

    }
}