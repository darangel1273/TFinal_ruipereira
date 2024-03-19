package com.ruipereira.tfinal_ruipereira.classesAuxiliares

/**
 * Teste com interfaces
 */
interface DocumentoValido {

    /**
     * Validação de Documentos -CheckDigit do Cartão de Cidadão
     * @see DocumentoValido
     * @param   numeroDocumento     String
     */
    fun validarCC(numeroDocumento: String): Boolean   //Obriga a implementar na classe pessoa

    /**
     * Conversão integrada no cálculo do Check-Digit para posterior validação do Cartão de Cidadão
     * @param   letra   Char
     * @return  Int
     */
    fun buscarNumeroDoCaractere(letra: Char): Int

    /**
     * Validação do Número de Contribuinte
     * (Não implementado)
     * @param   nif   Char
     * @return  Boolean
     */
    fun validarNIF(nif: String): Boolean
}