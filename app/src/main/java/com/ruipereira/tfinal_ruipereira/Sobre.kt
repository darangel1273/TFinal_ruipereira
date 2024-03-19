package com.ruipereira.tfinal_ruipereira

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ruipereira.tfinal_ruipereira.databinding.SobreBinding

/**
 * Classe que mostra curiosidades da APP, ex:Autor, Avaliação
 * @author  Rui Pereira
 */
class Sobre : AppCompatActivity() {
    private val binding by lazy { SobreBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imgSetaBaixo.visibility = View.GONE
        binding.imgSetaCima.setOnClickListener {
            binding.llVoltar.animate().translationX(binding.llVoltar.measuredHeight.toFloat() - 400)
                .setDuration(resources.getInteger(android.R.integer.config_longAnimTime).toLong())
            binding.llVoltar.animate().translationY(binding.llVoltar.measuredHeight.toFloat() - 200)
                .setDuration(resources.getInteger(android.R.integer.config_longAnimTime).toLong())
            binding.llVoltar.animate().translationZ(binding.llVoltar.measuredHeight.toFloat() - 100)
                .setDuration(resources.getInteger(android.R.integer.config_longAnimTime).toLong())
            binding.imgSetaCima.visibility = View.GONE
            binding.imgSetaBaixo.visibility = View.VISIBLE
        }
        binding.imgSetaBaixo.setOnClickListener {
            binding.llVoltar.animate().translationX(binding.llVoltar.measuredHeight.toFloat())
                .setDuration(resources.getInteger(android.R.integer.config_longAnimTime).toLong())
            binding.llVoltar.animate().translationY(binding.llVoltar.measuredHeight.toFloat())
                .setDuration(resources.getInteger(android.R.integer.config_longAnimTime).toLong())
            binding.llVoltar.animate().translationZ(binding.llVoltar.measuredHeight.toFloat())
                .setDuration(resources.getInteger(android.R.integer.config_longAnimTime).toLong())
            binding.imgSetaBaixo.visibility = View.GONE
            binding.imgSetaCima.visibility = View.VISIBLE
        }
        binding.btnVoltar.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }
}