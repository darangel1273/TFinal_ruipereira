package com.ruipereira.tfinal_ruipereira.classesAuxiliares

import android.content.ContentValues
import android.content.Intent
import android.widget.ImageView
import java.time.LocalDate
import java.time.Period

/**
 * SuperClasse que quarda os dados gerais da Pessoa
 * @see Contacto
 * @see <link>https://kotlinlang.org/docs/inheritance.html</link>
 * @author  Rui Pereira
 */
open class Pessoa : DocumentoValido {    //obriga a declarar a interface
    private var CC: String = "00"
    private var Nome: String = ""
    private var Morada: String = ""
    private var Nascimento: LocalDate = LocalDate.parse("1900-01-01")
    private var Sexo: String = "A"
    private var Idade: Period = Period.between(Nascimento, LocalDate.now())
    private lateinit var Foto: ImageView

    /**
     * Construtores
     */
    constructor(cc: String, nome: String, morada: String, nascimento: String, sexo: String) {
        setCC(cc);setNome(nome);setMorada(morada);setNasc(nascimento);setSexo(sexo)
    }

    /**
     * Setters
     */
    fun setCC(bi: String) {
        try {
            this.validarCC(bi)
        }                              //Chamada à função da interface
        catch (de: DocumentoException) {
            de.printStackTrace()
        } finally {
            this.CC = bi
        }                                  //Mesmo que seja inválido, aceita o CC na mesma.
    }

    fun setNome(nome: String) {
        this.Nome = nome
    }

    fun setMorada(morada: String) {
        this.Morada = morada
    }

    fun setSexo(sexo: String) {
        try {
            if (sexo != "M" && sexo != "F")
                throw java.lang.Exception()                       //Erro-Excepção Personalizada
            this.Sexo = sexo
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun setNasc(nascimento: String) {
        try {
            this.Nascimento = LocalDate.parse(nascimento); setIdade()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setIdade() {
        this.Idade = Period.between(this.Nascimento, LocalDate.now())
    }

    @Deprecated("Não Implementado")
    fun setFoto() {
        val iFoto: Intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        iFoto.addCategory(Intent.CATEGORY_OPENABLE)
        iFoto.setType("image/png")
    }

    /**
     * Getters
     */
    fun getCC(): String {
        return this.CC
    }

    fun getNome(): String {
        return this.Nome
    }

    fun getMorada(): String {
        return this.Morada
    }

    fun getSexo(): String {
        return this.Sexo
    }

    fun getNasc(): LocalDate {
        return this.Nascimento
    }

    fun getAnos(): Int {
        return (this.Idade.years)
    }

    /**
     * função que concatena os dados do objecto: Contacto numa String
     * @return  String
     */
    override fun toString(): String {
        return getCC() + ":" + getNome() + ":" + getMorada() + ":" + getNasc() + ":" + getAnos() + ":" + getSexo()
    }

    /**
     * Função que simplifica o envio do objecto: Contacto no Bundle dos Extras
     * @return   ret   ArrayList<Pessoa>
     */
    open fun toArrayList(): ArrayList<String> {
        val ret: ArrayList<String> = ArrayList()
        ret.add(getCC());ret.add(getNome()); ret.add(getMorada());ret.add(getNasc().toString());ret.add(
            getSexo()
        )
        return ret
    }

    /**
     * Cria uma embalagem de Content Values da Pessoa, par simplificar operações na Base de Dados
     * @return  ContentValues
     */
    open fun toCV(): ContentValues {
        var cv = ContentValues()
        cv.put("cc", getCC());cv.put("nome", getNome());cv.put(
            "morada",
            getMorada()
        );cv.put("nascimento", getNasc().toString())
        cv.put("sexo", getSexo())
        return cv
    }

    @Deprecated("Obsoleto", ReplaceWith("Construtor()"))
    fun fromArray(extras: Array<String>) {
        setNome(extras[0]);setMorada(extras[1]);setNasc(extras[2]);setSexo(extras[3])
    }

    /**
     * Implentação da Interface de validação de Documentos -CheckDigit do Cartão de Cidadão
     * @see DocumentoValido
     * @param   numeroDocumento     String
     */
    override fun validarCC(numeroDocumento: String): Boolean {
        var soma: Int = 0
        var segundodigito: Boolean = false
        var i: Int = 0
        if (numeroDocumento.length != 12) throw DocumentoException("Tamanho inválido para número de documento.")

        for (i in numeroDocumento.length - 1 downTo 0) {
            var valor: Int = buscarNumeroDoCaractere(numeroDocumento[i])
            if (segundodigito) {
                valor *= 2
                if (valor > 9) valor -= 9
            }
            soma += valor
            segundodigito = !segundodigito
        }
        return (soma % 10) == 0; }

    /**
     * Conversão integrada no cálculo do Check-Digit para posterior validação do Cartão de Cidadão
     * @param   letra   Char
     * @return  Int
     */
    override fun buscarNumeroDoCaractere(letra: Char): Int {
        when (letra) {
            '0' -> return 0
            '1' -> return 1
            '2' -> return 2
            '3' -> return 3
            '4' -> return 4
            '5' -> return 5
            '6' -> return 6
            '7' -> return 7
            '8' -> return 8
            '9' -> return 9
            'A' -> return 10
            'B' -> return 11
            'C' -> return 12
            'D' -> return 13
            'E' -> return 14
            'F' -> return 15
            'G' -> return 16
            'H' -> return 17
            'I' -> return 18
            'J' -> return 19
            'K' -> return 20
            'L' -> return 21
            'M' -> return 22
            'N' -> return 23
            'O' -> return 24
            'P' -> return 25
            'Q' -> return 26
            'R' -> return 27
            'S' -> return 28
            'T' -> return 29
            'U' -> return 30
            'V' -> return 31
            'W' -> return 32
            'X' -> return 33
            'Y' -> return 34
            'Z' -> return 35
        }
        throw DocumentoException("Valor Inválido no número de documento.")
    }
}