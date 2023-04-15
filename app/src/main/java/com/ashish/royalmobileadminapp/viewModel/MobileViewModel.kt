package com.ashish.royalmobileadminapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ashish.royalmobileadminapp.data.product.Product


class MobileViewModel : ViewModel() {
    var product = MutableLiveData<Product>()
    var msg = MutableLiveData<String>("")
}