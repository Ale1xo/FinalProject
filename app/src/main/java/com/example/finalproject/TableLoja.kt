package com.example.finalproject

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TableLoja(db :  SQLiteDatabase) : TabelaBD(db, NAME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$CAMPO_NAME TEXT NOT NULL," +
                "$LOCALIZACAO TEXT NOT NULL, "+
                "$IDMARCA INTEGER NOT NULL, "+
                "FOREIGN KEY ($IDMARCA) " +
                "REFERENCES ${TableMarcas.NOME}(${BaseColumns._ID})ON DELETE RESTRICT)")
    }

    companion object{
        const val NAME = "Loja"

        const val IDLOJA = "$NAME.${BaseColumns._ID}"
        const val IDMARCA = "MarcaId"
        const val CAMPO_NAME = "loja"
        const val LOCALIZACAO = "local"

        val TODAS_COLUNAS = arrayOf(IDLOJA, IDMARCA, CAMPO_NAME, LOCALIZACAO)
    }
}