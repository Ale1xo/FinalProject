package com.example.finalproject

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TableAvaliacao(db : SQLiteDatabase) : TabelaBD(db, NAME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID}INTEGER PRIMARY KEY AUTOINCREMENT" +
                "$AVALIACAO INTEGER NOT NULL)" +
                "FOREIGN KEY(${PRODUTO_ID}" +
                "REFERENCES ${TableProdutos.NAME}(${BaseColumns._ID}")
    }
    override fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor {
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = "$NAME INNER JOIN ${TableProdutos.NAME} ON ${TableProdutos.IDPRODUTO} = $PRODUTO_ID"

        return  queryBuilder.query(db,columns,selection,selectionArgs,groupBy,having,orderBy)
    }

    companion object{
        const val NAME = "Avaliacao"

        const val IDAVALIACAO = "Id"
        const val AVALIACAO = "Qualificacao"
        const val PRODUTO_ID = "ProdutoId"

        val TODAS_COLUNAS = arrayOf(IDAVALIACAO, AVALIACAO, PRODUTO_ID)
    }
}