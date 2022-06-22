package com.example.finalproject

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Avaliacao (var id: Long = -1, var avaliacao: Long, var idProduto: Long ){
    fun toContentValues(): ContentValues {
        val valoresAvaliacao = ContentValues()
        valoresAvaliacao.put(TableAvaliacao.AVALIACAO, avaliacao)
        valoresAvaliacao.put(TableAvaliacao.PRODUTO_ID, idProduto)
        return valoresAvaliacao
    }

    companion object {
        fun fromCursor(cursor: Cursor): Avaliacao{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posAvaliacao = cursor.getColumnIndex(TableAvaliacao.IDAVALIACAO)
            val posIdProduto = cursor.getColumnIndex(TableAvaliacao.PRODUTO_ID)

            val id = cursor.getLong(posId)
            val avaliacao = cursor.getLong(posAvaliacao)
            val idProduto = cursor.getLong(posIdProduto)

            return Avaliacao(id,avaliacao,idProduto)

        }
    }
}
