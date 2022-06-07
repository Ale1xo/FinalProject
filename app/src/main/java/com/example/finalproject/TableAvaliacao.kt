package com.example.finalproject

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TableAvaliacao(db : SQLiteDatabase) : TabelaBD(db, NAME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID}INTEGER PRIMARY KEY AUTOINCREMIDLO" +
                "$IDAVALIACAO INTEGER NOT NULL," +
                "$NAME TEXT NOT NULL)" +
                "$AVALIACAO TEXT NOT NULL)" +
                "$PRODUTO_ID INTEGER NOT NULL)" +
                "$LOJA_ID INTEGER NOT NULL)" +
                "FOREIGN KEY($PRODUTO_ID)" +
                "FOREIGN KEY($LOJA_ID)" +
                "REFERENCES ${TableProdutos.NAME}(${BaseColumns._ID}, ${TableLoja.NAME}(${BaseColumns._ID})")
    }

    companion object{
        const val  IDAVALIACAO = "Id"
        const val NAME = "Avalicao"
        const val AVALIACAO = "Qualificacao"
        const val PRODUTO_ID = "ProdutoId"
        const val LOJA_ID = "LojaId"
    }
}