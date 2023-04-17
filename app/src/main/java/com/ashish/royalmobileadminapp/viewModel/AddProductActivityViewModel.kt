package com.ashish.royalmobileadminapp.viewModel

import androidx.lifecycle.ViewModel
import com.ashish.royalmobileadminapp.data.product.Product

class AddProductActivityViewModel : ViewModel() {
    var product = Product()

    private fun setAllProduct(product: Product){
        this.product = product
    }
}