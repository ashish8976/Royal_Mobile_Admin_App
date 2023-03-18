package com.ashish.royalmobileadminapp.utils

import android.Manifest


object Constants {
    const val user_pref = "user"
    const val user_email = "email"
    const val PICK_IMAGE_REQUEST = 1

    const val brand = ""
    const val REQUEST_CODE_IMAGE=101
    const val TAG = "MyViewModel"
    const val REQUST_EXTERNAL_STORAGE = 2
    val PERMISSION_STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
}