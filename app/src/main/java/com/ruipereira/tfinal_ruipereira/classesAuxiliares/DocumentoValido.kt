package com.ruipereira.tfinal_ruipereira.classesAuxiliares

interface DocumentoValido {
    fun validarCC(numeroDocumento:String):Boolean   //Obriga a implementar na classe pessoa
    /**
     * Validação de Documentos -CheckDigit do Cartão de Cidadão
     * @see DocumentoValido
     * @param   numeroDocumento     String
     */
    fun buscarNumeroDoCaractere(letra:Char ):Int
    /**
     * Conversão integrada no cálculo do Check-Digit para posterior validação do Cartão de Cidadão
     * @param   letra   Char
     * @return  Int
     */
}