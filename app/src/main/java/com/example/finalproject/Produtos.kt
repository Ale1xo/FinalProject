package com.example.finalproject

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Produtos (var name: String, var preco : Long, var idMarca : Long , var id: Long = -1){
    fun toContentValues(): ContentValues {
        val valoresProdutos = ContentValues()
        valoresProdutos.put(TableProdutos.NAME, name)
        valoresProdutos.put(TableProdutos.PRECO, preco)
        valoresProdutos.put(TableProdutos.IDMARCA, idMarca)
        return valoresProdutos
    }

    companion object{
        fun fromCursor(cursor: Cursor):Produtos{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posName = cursor.getColumnIndex(TableProdutos.NAME)
            val posPreco = cursor.getColumnIndex(TableProdutos.PRECO)
            val posidMarca = cursor.getColumnIndex(TableProdutos.IDMARCA)

            val id = cursor.getLong(posId)
            val name = cursor.getString(posName)
            val preco = cursor.getLong(posPreco)
            val idMarca = cursor.getLong(posidMarca)

            return Produtos(name,preco,idMarca,id)
        }
    }

}