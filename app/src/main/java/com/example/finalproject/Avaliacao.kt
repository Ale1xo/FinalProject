package com.example.finalproject

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Avaliacao ( var avaliacao: Long, var produtos: Produtos ,var marcas: Marcas,var id: Long = -1){
    fun toContentValues(): ContentValues {
        val valoresAvaliacao = ContentValues()
        valoresAvaliacao.put(TableAvaliacao.AVALIACAO, avaliacao)
        valoresAvaliacao.put(TableAvaliacao.PRODUTO_ID, produtos.id)
        valoresAvaliacao.put(TableAvaliacao.IDMARCA, marcas.id)
        return valoresAvaliacao
    }

    companion object {
        fun fromCursor(cursor: Cursor): Avaliacao{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posAvaliacao = cursor.getColumnIndex(TableAvaliacao.IDAVALIACAO)
            val posIdProduto = cursor.getColumnIndex(TableProdutos.IDPRODUTO)
            val posNomeProduto = cursor.getColumnIndex(TableProdutos.CAMPO_NAME)
            val posIdMarca = cursor.getColumnIndex(TableProdutos.IDMARCA)
            val posNameMarca = cursor.getColumnIndex(TableProdutos.CAMPO_NAME)

            val id = cursor.getLong(posId)
            val avaliacao = cursor.getLong(posAvaliacao)

            val idProduto = cursor.getLong(posIdProduto)
            val nomeProduto = cursor.getString(posNomeProduto)
            val idMarca = cursor.getLong(posIdMarca)
            val nomeMarca = cursor.getString(posNameMarca)
            val produtos = Produtos(nomeProduto,idProduto,idMarca)
            val marcas = Marcas(nomeMarca,idMarca)

            return Avaliacao(avaliacao,produtos,marcas,id)

        }
    }
}
