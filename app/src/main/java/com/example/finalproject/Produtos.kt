package com.example.finalproject

import android.content.ContentValues

class Produtos (var idMarca: Long, var name: String){
    fun toContentValues(): ContentValues {
        val valoresProdutos = ContentValues()
        valoresProdutos.put(TableProdutos.NAME, name)
        valoresProdutos.put(TableProdutos.IDMARCA, idMarca)
        return valoresProdutos
    }
}