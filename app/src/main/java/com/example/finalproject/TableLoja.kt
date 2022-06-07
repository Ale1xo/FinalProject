package com.example.finalproject

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TableLoja(db :  SQLiteDatabase) : TabelaBD(db, NAME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID}INTEGER PRIMARY KEY AUTOINCREMIDLO" +
                "$IDLOJA INTEGER NOT NULL," +
                "$LOCALIZACAO TEXT NOT NULL)")
    }

    companion object{
        const val  IDLOJA = "Id"
        const val NAME = "loja"
        const val LOCALIZACAO = "local"
    }
}