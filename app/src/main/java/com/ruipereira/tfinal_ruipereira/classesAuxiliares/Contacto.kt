package com.ruipereira.tfinal_ruipereira.classesAuxiliares

import android.content.ContentValues

/**
 * Classe que herda de Pessoa que quarda os dados relativos aos contactos que a Pessoa tem
 * @see #Pessoa
 * @author  Rui Pereira
 */
class Contacto : Pessoa {
    private var telefone: String = ""
    private var telemovel: String = ""
    private var email: String = ""

    /**
     * Construtor 1 - Array de String
     * @param   ArrayList
     */
    constructor(a: ArrayList<String>) : super(a[0], a[1], a[2], a[3], a[4], a[5]) {
        setTelefone(a[6]); setTelemovel(a[7]); setEmail(a[8])
    }

    /**
     * Construtor 2 - Só de Strings
     *
     * @version 1
     */
    @Deprecated("Obsoleto com a introdução da uri e da imageView")//, ReplaceWith("construtor()"))
    constructor(
        bi: String,
        nome: String,
        morada: String,
        nascimento: String,
        sexo: String,
        telefone: String,
        telemovel: String,
        email: String,
    ) : super(bi, nome, morada, nascimento, sexo) {
        setTelefone(telefone); setTelemovel(telemovel); setEmail(email)
    }

    /**
     * Construtor 2 - Só de Strings, com uri da imageView
     *
     * @version 2
     */
    constructor(
        cc: String,
        nome: String,
        morada: String,
        nascimento: String,
        sexo: String,
        uri: String,
        telefone: String,
        telemovel: String,
        email: String,
    ) : super(cc, nome, morada, nascimento, sexo, uri) {
        setTelefone(telefone); setTelemovel(telemovel); setEmail(email);setUri(uri)
    }

    /**
     * Setters
     */
    fun setTelefone(telefone: String) {
        this.telefone = telefone
    }

    fun setTelemovel(telemovel: String) {
        this.telemovel = telemovel
    }

    fun setEmail(email: String) {
        this.email = email
    }

    /**
     * Getters
     */
    fun getTelefone(): String {
        return this.telefone
    }

    fun getTelemovel(): String {
        return this.telemovel
    }

    fun getEmail(): String {
        return this.email
    }

    /**
     * Parsers - toString() Adaptado para aparecer na ListView pelo adapter
     * @see Lista.kt
     */
    override fun toString(): String {
        return super.toString() + ":" + getTelefone() + ":" + getTelemovel() + ":" + getEmail()
    }

    /**
     * Método que converte o corrente objecto #Contacto num Array de Strings
     * (sobrepõe-se ao método da SuperClasse)
     * @return  ret
     */
    override fun toArrayList(): ArrayList<String> {
        val ret: ArrayList<String> = ArrayList()
        ret.addAll(super.toArrayList())
        ret.add(getTelefone());ret.add(getTelemovel());ret.add(getEmail())
        return ret
    }

    /**
     * Cria uma embalagem de Content Values do Contacto para simplificar as instruções de SQL no DBHelper
     * @see DBHelper
     * @return  cv  ContentValues
     */
    override fun toCV(): ContentValues {
        val cv = super.toCV()
        //cv.put("cc",getCC());cv.put("nome",getNome());cv.put("morada",getMorada());cv.put("nascimento",getNasc().toString() )
        //cv.put("sexo",getSexo());
        cv.put("telefone", getTelefone());cv.put("telemovel", getTelemovel());cv.put(
            "email",
            getEmail()
        )
        return cv
    }

    /**
     * Experiência para ordenar.....
     */
    @Deprecated("Não utilizado")
    fun compareTo(c: Contacto): Int {
        return this.getNome().compareTo(c.getNome())
    }
}