package com.example.finalproject

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TableMarcas(db : SQLiteDatabase) : TabelaBD(db, NAME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID}INTEGER PRIMARY KEY AUTOINCREMIDLO" +
                "$IDMARCA INTEGER NOT NULL," +
                "$NAME TEXT NOT NULL")
    }

    companion object{
        const val  IDMARCA = "Id"
        const val NAME = "Marca"
    }
}