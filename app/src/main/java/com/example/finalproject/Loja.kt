package com.example.finalproject

import android.content.ContentValues

class Loja(var idLoja: Long, var name : String, var localizacao: String) {

    fun toContentValues(): ContentValues{
        val valoresLoja = ContentValues()
        valoresLoja.put(TableLoja.NAME, name)
        valoresLoja.put(TableLoja.IDLOJA, idLoja)
        valoresLoja.put(TableLoja.LOCALIZACAO, localizacao)
        return valoresLoja
    }
}