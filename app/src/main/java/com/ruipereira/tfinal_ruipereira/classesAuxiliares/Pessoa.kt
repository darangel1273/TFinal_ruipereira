package com.ruipereira.tfinal_ruipereira.classesAuxiliares

import android.content.ContentValues
import android.content.Intent
import android.widget.ImageView
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

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
            validarCC(bi)
        }  //Chamada à função da interface
        catch (de: DocumentoException) {
            de.printStackTrace()
        } finally {
            this.CC = bi
        } //Mesmo que seja inválido, aceita o CC na mesma. (Devido a erros na validação do CC)
    }

    fun setNome(nome: String) {
        this.Nome = nome
    }

    fun setMorada(morada: String) {
        this.Morada = morada
    }

    fun setSexo(sexo: String) {
        try {
            if (sexo != "M" && sexo != "F") throw java.lang.Exception() //Erro-Excepção Personalizada
            this.Sexo = sexo
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun setNasc(nascimento: String) {
        try {
            this.Nascimento = LocalDate.parse(nascimento); setIdade()
        } catch (e: Exception) {
            e.printStackTrace();this.Nascimento = LocalDate.now(ZoneId.systemDefault())
        }//Se estiver errado atribui a data actual na mesma
        finally {
        }
    }

    fun setIdade() {
        this.Idade = Period.between(this.Nascimento, LocalDate.now(ZoneId.systemDefault()))
    }

    @Deprecated("(Não Implementado)")
    fun setFoto() {
        val iFoto = Intent(Intent.ACTION_OPEN_DOCUMENT)
        iFoto.addCategory(Intent.CATEGORY_OPENABLE)
        iFoto.setType("image/webp")
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
     * Cria uma embalagem de Content Values da Pessoa, para simplificar operações na Base de Dados
     * @return  ContentValues
     */
    open fun toCV(): ContentValues {
        val cv = ContentValues()
        cv.put("cc", getCC())
        cv.put("nome", getNome())
        cv.put("morada", getMorada())
        cv.put("nascimento", getNasc().toString())
        cv.put("sexo", getSexo())
        return cv
    }

    @Deprecated("Obsoleto", ReplaceWith("Construtor()"))
    fun fromArray(extras: Array<String>) {
        setNome(extras[0]);setMorada(extras[1]);setNasc(extras[2]);setSexo(extras[3])
    }

    /**
     * Implentação da Interface de validação de Documentos -CheckDigit do Cartão de Cidadão
     * @author  Autenticacao.gov
     * @see     <link>https://www.autenticacao.gov.pt/documents/20126/0/Valida%C3%A7%C3%A3o+de+N%C3%BAmero+de+Documento+do+Cart%C3%A3o+de+Cidad%C3%A3o+%281%29.pdf/7d5745ba-2bcc-e861-3954-bafe9f7591a0?version=1.0&t=1658411665319&previewFileIndex=4</link>
     * @see DocumentoValido
     * @param   numeroDocumento     String
     */
    override fun validarCC(numeroDocumento: String): Boolean {
        var soma = 0
        var segundodigito = false
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
        return (soma % 10) == 0
    }
    /**
     * Conversão integrada no cálculo do Check-Digit para posterior validação do Cartão de Cidadão
     *
     * @author  Autenticacao.gov
     * @see     <link>https://www.autenticacao.gov.pt/documents/20126/0/Valida%C3%A7%C3%A3o+de+N%C3%BAmero+de+Documento+do+Cart%C3%A3o+de+Cidad%C3%A3o+%281%29.pdf/7d5745ba-2bcc-e861-3954-bafe9f7591a0?version=1.0&t=1658411665319&previewFileIndex=4</link>
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

    /**
     * O NIF tem 9 dígitos, sendo o último o digito de controlo. Para ser calculado o digito de controlo:
     *
     * Multiplique o 8.º dígito por 2, o 7.º dígito por 3, o 6.º dígito por 4, o 5.º dígito por 5, o 4.º dígito por 6, o 3.º dígito por 7, o 2.º dígito por 8 e o 1.º dígito por 9;
     * Some os resultados;
     * Calcule o resto da divisão do número por 11;
     * Se o resto for 0 (zero) ou 1 (um) o dígito de controlo será 0 (zero);
     * Se for outro qualquer algarismo X, o dígito de controlo será o resultado da subtracção 11 - X.
     * @see     <link>https://github.com/marcolopes/dma/blob/master/org.dma.services.vies/src/org/dma/services/vies/VatNumber.java</link>
     * @author  org.dma.services.vies
     * @param   number  String
     * @return  Boolean
     */
    override fun validarNIF(number: String): Boolean {
        val max = 9
        //check if is numeric and has 9 numbers
        //check if is numeric and has 9 numbers
        if (!number.contains("0-9]+") || number.length != max) return false
        var checkSum = 0
        //calculate checkSum
        //calculate checkSum
        for (i in 0 until max - 1) {
            checkSum += (number[i] - '0') * (max - i)
        }
        var checkDigit = 11 - checkSum % 11
        //if checkDigit is higher than 9 set it to zero
        //if checkDigit is higher than 9 set it to zero
        if (checkDigit > 9) checkDigit = 0
        //compare checkDigit with the last number of NIF
        //compare checkDigit with the last number of NIF
        return checkDigit == number[max - 1] - '0'
    }
}