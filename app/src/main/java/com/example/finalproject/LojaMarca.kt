package com.example.finalproject

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class LojaMarca (var idLoja: Long, var idProduto: Long, var idMarca: Long, var id:Long = -1){
    fun toContentValues(): ContentValues{
        val valoresLojaMarca = ContentValues()
        valoresLojaMarca.put(TableLojaMarca.IDLOJA,idLoja)
        valoresLojaMarca.put(TableLojaMarca.IDPRODUTO,idProduto)
        valoresLojaMarca.put(TableLojaMarca.IDMARCA,idProduto)
        return valoresLojaMarca
    }
    companion object
    fun fromCursor(cursor: Cursor):LojaMarca{
        val posId = cursor.getColumnIndex(BaseColumns._ID)
        val posIdLoja = cursor.getColumnIndex(TableLoja.IDLOJA)
        val posIdProduto = cursor.getColumnIndex(TableProdutos.IDPRODUTO)
        val posIdMarca = cursor.getColumnIndex(TableMarcas.IDMARCA)

        val id = cursor.getLong(posId)
        val idLoja = cursor.getLong(posIdLoja)
        val idProduto = cursor.getLong(posIdProduto)
        val idMarca = cursor.getLong(posIdMarca)

        return LojaMarca(idLoja,idProduto,idMarca,id)
    }
}