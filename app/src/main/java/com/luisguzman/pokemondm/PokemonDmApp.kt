package com.luisguzman.pokemondm

import android.app.Application
import com.luisguzman.pokemondm.utils.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokemonDmApp : Application() {

    companion object {
        lateinit var sharedPreferences : Prefs
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = Prefs(applicationContext)
    }

}