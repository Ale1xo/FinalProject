package com.example.finalproject

import android.content.ContentValues

class Avaliacao (var idAvaliacao: Long, var name: String, var avaliacao: Long){
    fun toContentValues(): ContentValues {
        val valoresAvaliacao = ContentValues()
        valoresAvaliacao.put(TableAvaliacao.NAME, name)
        valoresAvaliacao.put(TableAvaliacao.IDAVALIACAO, idAvaliacao)
        valoresAvaliacao.put(TableAvaliacao.AVALIACAO, avaliacao)
        return valoresAvaliacao
    }
}
