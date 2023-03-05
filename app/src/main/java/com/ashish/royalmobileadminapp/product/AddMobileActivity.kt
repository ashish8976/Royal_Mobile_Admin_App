package com.ashish.royalmobileadminapp.product

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import com.ashish.royalmobileadminapp.R
import com.ashish.royalmobileadminapp.adapter.ColorSpinnerAdapter
import com.ashish.royalmobileadminapp.data.product.ColorList
import com.ashish.royalmobileadminapp.data.product.ColorObject
import com.ashish.royalmobileadminapp.databinding.ActivityAddMobileBinding

class AddMobileActivity : AppCompatActivity() {


    lateinit var binding : ActivityAddMobileBinding
    lateinit var selectColor : ColorObject
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMobileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadColorSpinner()
    }

//    val spinner = findViewById<Spinner>(R.id.spinner)
//    spinner.apply {
//        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val selectedItem = parent?.getItemAtPosition(position)
//                Log.d(TAG, "Selected item: $selectedItem")
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                Log.d(TAG, "No item selected")
//            }
//        }
//    }


    private fun loadColorSpinner()
    {
        selectColor = ColorList().defaultColor
        binding.colorSpinner.apply {
            adapter = ColorSpinnerAdapter(applicationContext,ColorList().basicColor())
            setSelection(ColorList().colorPosition(selectColor),false)

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                        selectColor = ColorList().basicColor()[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

        }
    }
}