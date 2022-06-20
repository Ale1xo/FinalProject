package com.example.finalproject

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TableProdutos(db : SQLiteDatabase) : TabelaBD(db, NAME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID}INTEGER PRIMARY KEY AUTOINCREMENT" +
                "$CAMPO_NAME TEXT NOT NULL," +
                "$IDMARCA INTEGER NOT NULL," +
                "$PRECO REAL NOT NULL," +
                "FOREIGN KEY ($IDMARCA), " +
                "REFERENCES ${TableMarcas.NAME}(${BaseColumns._ID})ON DELETE RESTRICT) ")
    }
    override fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor{
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = "$NAME INNER JOIN ${TableMarcas.NAME} ON ${TableMarcas.IDMARCA} = $IDMARCA"

        return  queryBuilder.query(db,columns,selection,selectionArgs,groupBy,having,orderBy)
    }

    companion object{
        const val NAME = "Produtos"

        const val IDPRODUTO = "$NAME.${BaseColumns._ID}"
        const val CAMPO_NAME = "Nome"
        const val IDMARCA = "MarcaId"
        const val PRECO = "Valor"

        val  TODAS_COLUNAS = arrayOf(IDPRODUTO, CAMPO_NAME, IDMARCA, PRECO)
    }
}