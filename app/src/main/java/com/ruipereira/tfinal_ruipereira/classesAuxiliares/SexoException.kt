package com.ruipereira.tfinal_ruipereira.classesAuxiliares

/**
 * Classe inicialmente pensada para validar entradas inválidas no Formulário - Caracteres do Sexo
 */
@Deprecated("Não chegou a ser validado, agora com os radio buttons", ReplaceWith("Exception"))
class SexoException :
    Exception("Caractere Inválido na Definição do Sexo. Apenas são válidos: 'S' ou 'M' ")