package com.ashish.royalmobileadminapp.utils

import android.Manifest
import com.ashish.royalmobileadminapp.URL


object Constants {
    const val user_pref = "user"
    const val user_register = "register"
    const val user_email = "email"
    const val user_email_details="email_admin"
    const val PICK_IMAGE_REQUEST = 1
    const val category = ""
    const val brand = ""
    const val REQUEST_CODE_IMAGE=101
    const val TAG = "MyViewModel"
    const val REQUST_EXTERNAL_STORAGE = 2
    val PERMISSION_STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    fun urlMaker(imageurl :String): String {
        val fileName = imageurl.substringAfter("http://localhost:8007/storage/images/")
        return URL.IMAGE_PATH+fileName
        //http://localhost:8007/storage/images/image1679465705866.jpg
    }
}