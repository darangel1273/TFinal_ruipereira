package com.ruipereira.tfinal_ruipereira

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.ruipereira.tfinal_ruipereira.databinding.SplashBinding

/**
 * Classe de lançamento desta APP - MyFriendz - Sem interactividade
 * Apenas mostra o logotipo feito para a App
 * @author  Rui Pereira
 */
class Splash : AppCompatActivity() {
    private val binding by lazy { SplashBinding.inflate(layoutInflater) }
    private lateinit var iPino: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            iPino = Intent(this, Pino::class.java)
            startActivity(iPino)
        }, 1000)
    }
}