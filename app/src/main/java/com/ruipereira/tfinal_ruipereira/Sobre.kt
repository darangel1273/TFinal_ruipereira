package com.ruipereira.tfinal_ruipereira

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ruipereira.tfinal_ruipereira.databinding.SobreBinding

/**
 * Classe que mostra curiosidades da APP, ex:Autor, Avaliação
 * @author  Rui Pereira
 */
const val dur: Long = 1000
class Sobre : AppCompatActivity() {
    private val binding by lazy { SobreBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imgSetaBaixo.visibility = View.GONE
        binding.imgSetaCima.setOnClickListener {
            anima(400, 200, 100)
            binding.imgSetaCima.visibility = View.GONE
            binding.imgSetaBaixo.visibility = View.VISIBLE
        }
        binding.imgSetaBaixo.setOnClickListener {
            anima(0, 0, 0)
            binding.imgSetaBaixo.visibility = View.GONE
            binding.imgSetaCima.visibility = View.VISIBLE
        }
        binding.btnVoltar.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }

    fun anima(deltaX: Int, deltaY: Int, deltaZ: Int) {
        binding.llVoltar.animate().translationX(binding.llVoltar.measuredHeight.toFloat() - deltaX)
            .setDuration(dur)
        binding.llVoltar.animate().translationY(binding.llVoltar.measuredHeight.toFloat() - deltaY)
            .setDuration(dur)
        binding.llVoltar.animate().translationZ(binding.llVoltar.measuredHeight.toFloat() - deltaZ)
            .setDuration(dur)
//        binding.llVoltar.animate().translationX(binding.llVoltar.measuredHeight.toFloat() - deltaX )
//            .setDuration(resources.getInteger(android.R.integer.config_longAnimTime).toLong())
//        binding.llVoltar.animate().translationY(binding.llVoltar.measuredHeight.toFloat() - deltaY )
//            .setDuration(resources.getInteger(android.R.integer.config_longAnimTime).toLong())
//        binding.llVoltar.animate().translationZ(binding.llVoltar.measuredHeight.toFloat() - deltaZ )
//            .setDuration(resources.getInteger(android.R.integer.config_longAnimTime).toLong())
    }
}