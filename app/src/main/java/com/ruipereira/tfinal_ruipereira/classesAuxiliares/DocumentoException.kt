package com.ruipereira.tfinal_ruipereira.classesAuxiliares

import android.util.Log

/**
 * Classe inicialmente pensada para validar entradas inválidas no Formulário
 * Cartão de Cidadão
 */
class DocumentoException : Exception{
    private var etiqueta:String="ERRO:Cartão de Cidadão"
    constructor(erro:String){
        Log.e(etiqueta,erro)
        this.printStackTrace()
    }
    override fun printStackTrace() {
        Log.e(etiqueta,"Erro na Introdução do Número do Cartão de Cidadão")
        super.printStackTrace()
    }
}