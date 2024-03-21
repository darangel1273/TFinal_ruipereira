package com.ruipereira.tfinal_ruipereira

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.ruipereira.tfinal_ruipereira.bd.DBHelper
import com.ruipereira.tfinal_ruipereira.classesAuxiliares.Contacto
import com.ruipereira.tfinal_ruipereira.databinding.DetalheBinding

/**
 * Classe que vai fazer a gestão dos detalhes do Contacto
 * @author  Rui Pereira
 */
class Detalhe : AppCompatActivity() {
    private lateinit var stringS: ArrayList<String>
    private lateinit var iLigar: Intent
    private val binding by lazy { DetalheBinding.inflate(layoutInflater) }
    private var resultado = 0
    private lateinit var iLista: Intent
    private lateinit var pack: Bundle
    private lateinit var c: ArrayList<Contacto>
    private var indice: Int = 0
    private lateinit var ligarDB: DBHelper
    private lateinit var res: ActivityResultLauncher<Intent>
    private lateinit var imgUrl: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ligarDB = DBHelper(
            applicationContext,
            "contactos.db",
            null
        )//Inicializa o Controlador da Base de dados
        binding.btnRemover.visibility =
            View.VISIBLE                    // mostra sempre o botão para apagar, excepto quando for adicionar
        val beep = MediaPlayer.create(applicationContext, R.raw.click)
        stringS =
            ArrayList()                                           // Inicializa o array de Strings que vai receber os extras
        c =
            ArrayList()                                                 // Abrir o Array que vai ter os dados à "chegada" e à "saída"
        iLista = intent                                                 // Recuperar o Intent
        pack =
            iLista.extras!!                                          // Extrair os extras do Bundle
        // Enche o form com dados, desde que a operação não seja adicionar
        if (pack.getString("Operacao", "") == "Adicionar") {// Formulário com os dados preenchidos
            binding.btnRemover.visibility =
                View.GONE                    // Se adicionar, não vai remover
            binding.lblOperacao.text =
                pack.getString("Operacao", R.string.operacaoinvalida.toString())
        } else {
            load2form()                                               // form limpo para um novo registo
        }                                                             // Ver os Detalhes do Contacto
        binding.btnVoltar.setOnClickListener {                 // Apenas para ver, só volta sem nada para fazer
            sair(beep)
        }
        binding.imgCamara.setOnClickListener {                  // Abrir a camara
            val iCam = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            res.launch(iCam)
        }
        binding.imgGaleria.setOnClickListener {
            val iGal = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            res.launch(iGal)
        }
        binding.txtCc.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (ligarDB.jaExiste(binding.txtCc.text.toString())) { // Vai à BD ver se o cc já existe
                    Toast.makeText(this, R.string.duplicado, Toast.LENGTH_LONG).show()
                    binding.txtCc.requestFocus()                      // se existir, obriga a introduzir outro
                }
            }
        }
        binding.txtNasc.setOnFocusChangeListener { v, hasFocus ->
            try {                                                     //depois de mexer na data de nascimento
                if (!hasFocus) binding.txtIdade.text = calculaIdade()
            }                                                         //quando sair, calcula a idade
            catch (e: Exception) {
                binding.txtNasc.requestFocus()
            }     //Se houver erro na data de nascimento
        }                                                             //Volta a introduzi-la
        binding.btnLigarTlf.setOnClickListener {
            chamar(binding.txtTelefone.text.toString(), beep)         // Chamar para telefone fixo
        }
        binding.btnLigarTlm.setOnClickListener {
            chamar(binding.txtTelemovel.text.toString(), beep)        // Chamar para telemovel
        }
        binding.btnRemover.setOnClickListener {
            resultado =
                2                                              // informação para apagar o registo
            pack.putString(
                "Operacao",
                "Remover"
            )                      // informação para apagar o registo
            sair(beep)
        }
        binding.btnGravar.setOnClickListener {// Este botão pode adicionar um registo novo ou gravar dados editados
            save2extras()
            sair(beep)
        }
        // retorno dos Intents
        res = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (resultado == RESULT_OK) {
                imgUrl = Uri.parse(it.data.toString())
                binding.imgFoto.setImageURI(imgUrl)
            }
        }
    }
    /**
     * Função que embala o registo do Contacto; a posicao no array; e a operação a fazer; dentro do Bundle
     * para posteriormente ser anexado ao Intent.
     * Detecta se houve alterações ao registo (Contacto) que veio da #Lista c (indice) cria mais outro -alterado ou não
     * e em seguida compara-os.
     * @see Lista
     */
    private fun save2extras() {
        var sx = "NB"
        if (binding.radMasculino.isChecked) {
            sx = "M"
        }
        if (binding.radFeminino.isChecked) {
            sx = "F"
        }       //Validar a sigla do Sexo
        c.add(
            Contacto(
                binding.txtCc.text.toString(), // Criar outro contacto com os dados "actualizados"
                binding.txtNome.text.toString(),
                binding.txtMorada.text.toString(), binding.txtNasc.text.toString(),
                sx, binding.txtTelefone.text.toString(), binding.txtTelemovel.text.toString(),
                binding.txtEmail.text.toString()
            )
        )                // Carrega os dados do form no 2ºIndice
        if (binding.lblOperacao.text.toString() != "Adicionar") {
            if (c[indice] != c[indice + 1]) {                   // Ver se houve alterações nos dados do form
                indice =
                    1                                      //há 2 registos diferentes: o que veio, e o que foi alterado
                resultado =
                    1                                   // Quando voltar à Lista, informa que houve alterações ao registo
                Toast.makeText(this, R.string.registoalterado, Toast.LENGTH_SHORT).show()
                if (pack.getString(
                        "Operacao",
                        ""
                    ) == "Ver"
                ) { // Mudar o estado de VER para EDITAR, já que
                    pack.putString(
                        "Operacao",
                        "Editar"
                    )         // houve alterações, entre o contacto que veio e o contacto
                }                                               // que vai, estas alterações têm de ser enviadas para a Lista
            }
        } else if (binding.lblOperacao.text.toString() == "Adicionar") {
            resultado = 3
            indice =
                0                                            // Não há registo anterior, apenas um
            pack.putString(
                "Operacao",
                "Adicionar"
            )                //foi pedido o Adicionar na operação Anterior
        }
        pack.putStringArrayList(
            "Contacto",
            c[indice].toArrayList()
        )// Actualiza os dados do Contacto para o extra
    }

    /**
     * função que extrai do Bundle: o registo ( Contacto que vem no array de strings ); a posição no array, e a operação pedida para realizar quando voltar
     * para o formulário
     * @see Bundle
     */
    private fun load2form() {
        stringS =
            pack.getStringArrayList("Contacto")!!      // Extrair os extras do Contacto no bundle
        c.add(Contacto(stringS))                               // Carrega o 1ºIndice com os extras
        binding.lblPosRegisto.text = pack.getInt("Posicao", -1).toString()
        binding.lblOperacao.text =
            pack.getString("Operacao", "") //Controlo a fazer no Detalhe que veio da Lista
        binding.txtCc.setText(c[0].getCC())                // Carrega o Objecto para o form
        binding.txtNome.setText(c[0].getNome())                // Carrega o Objecto para o form
        binding.txtMorada.setText(c[0].getMorada())
        binding.txtNasc.setText(c[0].getNasc().toString())
        binding.txtIdade.text = calculaIdade()
        if (c[0].getSexo() == "M")
            binding.grpSexo.check(R.id.rad_masculino)
        if (c[0].getSexo() == "F")
            binding.grpSexo.check(R.id.rad_feminino)
        binding.txtTelefone.setText(c[0].getTelefone())
        binding.txtTelemovel.setText(c[0].getTelemovel())
        binding.txtEmail.setText(c[0].getEmail())
    }

    /**
     * função que anexa ao Intent, o Bundle construido (anteriormente noutas funções )
     * e depois sái desta activity, e retorna à lista Activity
     */
    private fun sair(beep: MediaPlayer) {
        beep.start()
        iLista = Intent(this, Lista::class.java)
        iLista.putExtras(pack)
        setResult(resultado, iLista)
        finish()
    }

    /**
     * Função activada sempre que se sai da data de nascimento
     * @return  String
     */
    private fun calculaIdade(): String {
        c[0].setNasc(binding.txtNasc.text.toString())
        return c[0].getAnos().toString()  // .toString().plus(R.string.anos)
//        return String.format(" %s ", c[0].getAnos().toString().plus(R.string.anos ) )  // .toString().plus(R.string.anos)
//        return String.format(Locale.forLanguageTag("PT_PT"),"%d %s", c[0].getAnos(), R.string.anos
//        return String.format("%d %s", c[0].getAnos(), R.string.anos )  // .toString().plus(R.string.anos)
    }

    /**
     * Função que constroi o número de chamada e chama o Intent ( APP ) para efectuar chamadas
     *
     * @see Intent
     * @param String
     * @param MediaPlayer
     * */
    private fun chamar(num: String, b: MediaPlayer) {
        b.start()
//            if ( checkSelfPermission( android.Manifest.permission.CALL_PHONE ) == PackageManager.PERMISSION_GRANTED) {
//        iLigar = Intent( Intent.ACTION_CALL, Uri.parse("tel:$num") )
        iLigar = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$num"))
        startActivity(iLigar)
//        }
    }
}