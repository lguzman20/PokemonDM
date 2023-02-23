package com.luisguzman.pokemondm.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.luisguzman.pokemondm.PokemonDmApp.Companion.sharedPreferences
import com.luisguzman.pokemondm.databinding.ActivitySplashBinding
import com.luisguzman.pokemondm.ui.auth.AuthActivity
import com.luisguzman.pokemondm.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkNavigation()
    }

    private fun checkNavigation() {
        val checkLogin = sharedPreferences.getCheckLogin()
        Handler(Looper.getMainLooper()).postDelayed({
            if (checkLogin) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, AuthActivity::class.java))
            }
            finish()
        }, 3000)
    }
}