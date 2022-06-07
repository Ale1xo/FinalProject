package com.example.finalproject

import android.content.ContentValues

class Produtos (var idMarca: Long, var name: String, var preco : Long){
    fun toContentValues(): ContentValues {
        val valoresProdutos = ContentValues()
        valoresProdutos.put(TableProdutos.NAME, name)
        valoresProdutos.put(TableProdutos.IDMARCA, idMarca)
        valoresProdutos.put(TableProdutos.PRECO, preco)
        return valoresProdutos
    }
}