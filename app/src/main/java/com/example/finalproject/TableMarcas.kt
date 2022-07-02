package com.example.finalproject

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TableMarcas(db : SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$CAMPO_NAME TEXT NOT NULL)")
    }

    companion object{
        const val NOME = "Marcas"

        const val IDMARCA = "$NOME.${BaseColumns._ID}"
        const val CAMPO_NAME = "Name"

        val TODAS_COLUNAS = arrayOf(IDMARCA, CAMPO_NAME)
    }
}