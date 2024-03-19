package com.ruipereira.tfinal_ruipereira

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.ruipereira.tfinal_ruipereira.databinding.PinoBinding

/**
 * Classe que pede autenticação do utlizador com a introdução de um pino
 *
 * @author  Rui Pereira
 */
class Pino : AppCompatActivity() {
    private val binding by lazy { PinoBinding.inflate(layoutInflater) }
    private lateinit var pack: Bundle
    private lateinit var iLista: Intent
    lateinit var res: ActivityResultLauncher<Intent>
    private lateinit var iDef: Intent
    private var p: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val beep = MediaPlayer.create(this, R.raw.click)
        binding.btnLogin.setOnClickListener {
            beep.start()
            val prefPino = this.getSharedPreferences("definicoes", Context.MODE_PRIVATE)
            val pino = prefPino.getString("pino", R.string.erro.toString())
            p = binding.txtPino.text.toString()
            if (p == pino) {
                Toast.makeText(this, R.string.loginvalido, Toast.LENGTH_SHORT).show()
                iLista = Intent(this, Lista::class.java)
                pack = Bundle()
                pack.putString("pino", pino)
                iLista.putExtras(pack)
                res.launch(iLista)
            } else {
                Toast.makeText(this, R.string.logininvalido, Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnDef.setOnClickListener {
            beep.start()
            iDef = Intent(this, Definicoes::class.java)

            res.launch(iDef)
        }
        res = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == 2) {
                Toast.makeText(this, R.string.pinoalterado, Toast.LENGTH_SHORT).show()
            }
        }
    }
}