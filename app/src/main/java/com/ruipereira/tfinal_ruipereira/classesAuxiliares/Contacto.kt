package com.ruipereira.tfinal_ruipereira.classesAuxiliares

import android.content.ContentValues

/**
 * Classe que herda de Pessoa que quarda os dados relativos aos contactos que a Pessoa tem
 * @see #Pessoa
 * @author  Rui Pereira
 */
class Contacto : Pessoa {
    private var Telefone: String = ""
    private var Telemovel: String = ""
    private var Email: String = ""

    /**
     * Construtor 1 - Array de String
     * @param   ArrayList
     */
    constructor(a: ArrayList<String>) : super(a[0], a[1], a[2], a[3], a[4]) {
        setTelefone(a[5]); setTelemovel(a[6]); setEmail(a[7])
    }

    /**
     * Construtor 2 - Só de Strings
     */
    constructor(
        bi: String,
        nome: String,
        morada: String,
        nascimento: String,
        sexo: String,
        telefone: String,
        telemovel: String,
        email: String
    ) : super(bi, nome, morada, nascimento, sexo) {
        setTelefone(telefone); setTelemovel(telemovel); setEmail(email)
    }

    /**
     * Setters
     */
    fun setTelefone(telefone: String) {
        this.Telefone = telefone
    }

    fun setTelemovel(telemovel: String) {
        this.Telemovel = telemovel
    }

    fun setEmail(email: String) {
        this.Email = email
    }

    /**
     * Getters
     */
    fun getTelefone(): String {
        return this.Telefone
    }

    fun getTelemovel(): String {
        return this.Telemovel
    }

    fun getEmail(): String {
        return this.Email
    }

    /**
     * Parsers
     */
    override fun toString(): String {
        return getNome()
    }//super.toString()+":"+getTelefone()+":"+getTelemovel()+":"+getEmail() }

    override fun toArrayList(): ArrayList<String> {
        val ret: ArrayList<String> = ArrayList()
        ret.addAll(super.toArrayList())
        ret.add(getTelefone());ret.add(getTelemovel());ret.add(getEmail())
        return ret
    }

    /**
     * Cria uma embalagem de Content Values do Contacto para simplificar as instruções de SQL no DBHelper
     * @see DBHelper
     * @return  ContentValues
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