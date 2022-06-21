package com.example.finalproject

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TableLojaMarca (db:SQLiteDatabase):TabelaBD(db,NAME){
    override fun cria(){
        db.execSQL("CREATE $nome(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FOREIGN KEY ($IDLOJA), " +
                "REFERENCES ${TableLoja.NAME}(${BaseColumns._ID})ON DELETE RESTRICT)"+
                "FOREIGN KEY ($IDPRODUTO), " +
                "REFERENCES ${TableProdutos.NAME}(${BaseColumns._ID})ON DELETE RESTRICT)"+
                "FOREIGN KEY ($IDMARCA), " +
                "REFERENCES ${TableMarcas.NAME}(${BaseColumns._ID})ON DELETE RESTRICT)")
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
        queryBuilder.tables = "$NAME INNER JOIN ${TableLoja.NAME} ON ${TableLoja.IDLOJA} = $IDLOJA"
        queryBuilder.tables = "$NAME INNER JOIN ${TableProdutos.NAME} ON ${TableProdutos.IDPRODUTO} = $IDPRODUTO"
        queryBuilder.tables = "$NAME INNER JOIN ${TableMarcas.NAME} ON ${TableMarcas.IDMARCA} = $IDMARCA"

        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object{
        const val NAME = "LojaMarca"

        const val ID_LOJAMARCA = "$NAME.${BaseColumns._ID}"
        const val IDLOJA = "LojaId"
        const val IDPRODUTO = "ProdutoId"
        const val IDMARCA = "MarcaId"

        val TODAS_COLUNAS = arrayOf(ID_LOJAMARCA, IDLOJA, IDPRODUTO, IDMARCA)
    }
}