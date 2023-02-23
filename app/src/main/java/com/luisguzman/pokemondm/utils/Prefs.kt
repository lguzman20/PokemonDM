package com.luisguzman.pokemondm.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    private val storage: SharedPreferences = context.getSharedPreferences("SharedPreferences", 0)

    fun setLoginValue(save:Boolean) {
        storage.edit().putBoolean("saveFlag", save).apply()
    }

    fun getCheckLogin() : Boolean = storage.getBoolean("saveFlag", false)
}