package com.ruipereira.tfinal_ruipereira

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.ruipereira.tfinal_ruipereira.bd.DBHelper
import com.ruipereira.tfinal_ruipereira.classesAuxiliares.Contacto
import com.ruipereira.tfinal_ruipereira.databinding.ListaBinding

/**
 * Classe que lista os Contactos e que os ordena por ordem Descendente e Ascendente.
 * Activity Principal - Gestão da Lista
 * @author   Rui Pereira
 */
class Lista : AppCompatActivity() {
    private val binding by lazy { ListaBinding.inflate(layoutInflater) }
    private lateinit var contS: ArrayList<Contacto>
    private var pos: Int = -1
    private var operacao: String = "Ver"
    private lateinit var iDet: Intent
    private lateinit var res: ActivityResultLauncher<Intent>
    private lateinit var ligarBD: DBHelper
    private lateinit var pack: Bundle
    private var resultado: Int = -1
    private lateinit var beep: MediaPlayer
    private lateinit var max: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        beep = MediaPlayer.create(applicationContext, R.raw.click);max =
            MediaPlayer.create(applicationContext, R.raw.max)
        ligarBD = DBHelper(
            applicationContext,
            "contactos.db",
            null
        )//Inicializa o Controlador da Base de dados
        //contS = ArrayList()                                         // Inicializa o ArrayList
        contS =
            ligarBD.carregar()                                        // Carrega da Base de dados
        // contS =ligar.load2List()                                      // Carrega dados estáticos
        adaptar()
        binding.lstContactos.setOnItemClickListener { parent, view, position, id ->
            max.start()
            pos = position
            lancar("Ver")
        }
        binding.btnVoltar.setOnClickListener {

            beep.start()
            setResult(resultado)
            finish()
        }
        binding.btnNovo.setOnClickListener {
            beep.start()
            lancar("Adicionar")
        }
        binding.btnSobre.setOnClickListener {
            res.launch(Intent(this, Sobre::class.java))
        }
        /**
         * Para ordenar, cria um novo array odenado, com base no #contS
         * e copia novamente para #contS
         */
        binding.btnDescende.setOnClickListener {
            val desc = contS.sortedWith(compareBy { it.getNome() }).reversed()
            contS = ArrayList()             //inicializa o array novamente
            contS.addAll(desc)              //copia do desc outra vez para cont
            adaptar()
        }
        binding.btnAscende.setOnClickListener {
            val asc = contS.sortedWith(compareBy { it.getNome() })
            contS = ArrayList()
            contS.addAll(asc)
            adaptar()
        }

        /**
         * Retorno do Detalhe e validar a operação pedida no #Detalhe
         * recebe o codigo de resultado (juntamente com a operação que vem do extra, agora é redundante)
         * extrai a operação que já tinha sido enviada e voltou a ser recebida
         * extrai a posição no Array de Contactos #contS que já tinha sido enviada (excepto adicionar) e voltou a ser recebida
         */
        res = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if ((it.data?.extras?.getString("Operacao", "") != null)) {
                operacao = it.data?.extras?.getString("Operacao", "")!!
                if (it.resultCode != RESULT_OK) //Veio da Activity Sobre? - resultCode = -1
                    if (operacao != "Adicionar") {              // recupera a posição que foi enviada daqui
                        pos = it.data?.extras?.getInt("Posicao", -1)!!
                    }//A adicionar, não há transporte da pos do ArrayList
                if (it.resultCode == 0)                          //Só Voltou, não houve alterações
                    Toast.makeText(applicationContext, R.string.registoigual, Toast.LENGTH_SHORT)
                        .show()
                if (it.resultCode == 2)                         //indicação de Apagar o registo
                    if (operacao == "Remover") {             //Apagar o Contacto que veio do detalhe
                        ligarBD.apaga(contS[pos])            // Apaga da Base de Dados
                        contS.removeAt(pos)                 // Apaga do ArrayList
                    }
                if (it.resultCode == 1)                    //Houve alterações entre o registo que foi e o que voltou
                    if (operacao == "Editar") {
                        ligarBD.atualiza(Contacto(it.data?.extras?.getStringArrayList("Contacto")!!))          // Faz update na tabela contacto
                        contS[pos] = Contacto(it.data?.extras?.getStringArrayList("Contacto")!!)
                    }
                if (it.resultCode == 3)                    //Houve alterações entre o registo que foi e o que voltou
                    if (operacao == "Adicionar") {          //Adicionar um novo Contacto
                        ligarBD.insere(Contacto(it.data?.extras?.getStringArrayList("Contacto")!!))
                        contS.add(Contacto(it.data?.extras?.getStringArrayList("Contacto")!!))
                    }
                adaptar()                                  //Refrescar as alterações
            }
        }
    }

    /**
     * Lança o Intent para o #Detalhe
     *
     * @see     Detalhe     Informação dos Contactos
     * @param   operacao    Tipo de Operação a validar no Detalhe
     * @return  #Não retorna parâmetros
     */
    fun lancar(operacao: String) {
        iDet = Intent(this, Detalhe::class.java)
        pack = Bundle()
        pack.putInt("Posicao", pos)
        pack.putString("Operacao", operacao)
        if (operacao != "Adicionar")
            pack.putStringArrayList("Contacto", contS[pos].toArrayList())
        else
            pack.putStringArrayList(
                "Contacto",
                Contacto("0", "n", "m", "1900-01-01", "M", "00", "00", "a@b.pt").toArrayList()
            )
        iDet.putExtras(pack)
        res.launch(iDet)
    }

    /**
     * Cria o adaptador que liga a ListView ao array de contactos #contS e actualiza a ListView
     * privada - Só pode ser chamada aqui dentro desta classe
     */
    private fun adaptar() {   //Associa o adaptador ao Array
        val adapta = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, contS)
        binding.lstContactos.adapter = adapta                   // Define adaptador
        adapta.notifyDataSetChanged()                           // Actualiza a listView
    }
}