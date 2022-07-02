package com.example.finalproject

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Marcas( var name : String,var id : Long = -1) {
    fun toContentValues(): ContentValues {
        val valoresMarca = ContentValues()
        valoresMarca.put(TableMarcas.CAMPO_NAME,name)

        return valoresMarca
    }
    companion object{
        fun fromCursor( cursor: Cursor): Marcas{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posName = cursor.getColumnIndex(TableMarcas.NOME)

            val id = cursor.getLong(posId)
            val name = cursor.getString(posName)

            return Marcas(name,id)
        }
    }
}