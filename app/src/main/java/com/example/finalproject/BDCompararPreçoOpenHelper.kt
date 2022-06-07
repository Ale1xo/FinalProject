package com.example.finalproject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDCompararPreçoOpenHelper(context : Context?) : SQLiteOpenHelper(context, NOME, null, VERSAO ) {

    override fun onCreate(db : SQLiteDatabase){
        requireNotNull(db)
        TableAvaliacao(db).cria()
        TableProdutos(db).cria()
        TableLoja(db).cria()
        TableMarcas(db).cria()
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    companion object{
        const val NOME = "CompararPreco.db"
        private const val VERSAO = 1
    }
}