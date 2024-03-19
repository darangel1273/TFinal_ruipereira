package com.ruipereira.tfinal_ruipereira

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ruipereira.tfinal_ruipereira.databinding.DefinicoesBinding

/**
 * @author  Rui Pereira
 * Classe que serve para alterar o pino do utilizador
 */
class Definicoes : AppCompatActivity() {
    private var resultado: Int = 0
    private val binding by lazy { DefinicoesBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val beep = MediaPlayer.create(applicationContext, R.raw.click)
        binding.btnVoltar.setOnClickListener {
            beep.start()
            setResult(resultado)
            finish()
        }
        binding.btnAlterarPino.setOnClickListener {
            beep.start()
            if (binding.txtPino1.text.toString() == binding.txtPino2.text.toString()) {
                resultado = 2
                val prefPino = getSharedPreferences("definicoes", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = prefPino.edit()
                editor.putString("pino", binding.txtPino1.text.toString())
                editor.apply()
                Toast.makeText(applicationContext, R.string.pinoalterado, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    R.string.pinosnaocorrespondem,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}