package com.example.finalproject

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TableProdutos(db : SQLiteDatabase) : TabelaBD(db, NAME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID}INTEGER PRIMARY KEY AUTOINCREMIDLO" +
                "$IDPRODUTO INTEGER NOT NULL," +
                "$NAME TEXT NOT NULL," +
                "$IDMARCA INTEGER NOT NULL," +
                "$PRECO REAL NOT NULL," +
                "FOREIGN KEY ($IDMARCA)), " +
                "REFERENCES ${TableMarcas.NAME}(${BaseColumns._ID}) ")
    }

    companion object{
        const val  IDPRODUTO = "Id"
        const val NAME = "Produto"
        const val IDMARCA = "MarcaId"
        const val PRECO = "Valor"
    }
}