package com.example.finalproject

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Loja( var name : String, var localizacao: String,var id: Long = -1) {

    fun toContentValues(): ContentValues{
        val valoresLoja = ContentValues()
        valoresLoja.put(TableLoja.NAME, name)
        valoresLoja.put(TableLoja.LOCALIZACAO, localizacao)
        return valoresLoja
    }

    companion object{
        fun fromCursor(cursor:Cursor): Loja{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posName = cursor.getColumnIndex(TableLoja.NAME)
            val posLocalizacao = cursor.getColumnIndex(TableLoja.LOCALIZACAO)

            val id = cursor.getLong(posId)
            val name = cursor.getString(posName)
            val localizacao = cursor.getString(posLocalizacao)

            return Loja(name,localizacao,id)
        }
    }
}