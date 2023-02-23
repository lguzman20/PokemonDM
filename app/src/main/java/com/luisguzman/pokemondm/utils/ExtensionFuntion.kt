package com.luisguzman.pokemondm.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible

fun errorToastMessage(context:Context, message:String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun successToastMessage(context:Context, message:String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun View.gone() {
    isVisible = false
}

fun View.visible() {
    isVisible = true
}