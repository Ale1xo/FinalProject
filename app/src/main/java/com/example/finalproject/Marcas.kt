package com.example.finalproject

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Marcas(var id : Long = -1, var name : String) {
    fun toContentValues(): ContentValues {
        val valoresMarca = ContentValues()
        valoresMarca.put(TableLoja.NAME, name)

        return valoresMarca
    }
    companion object{
        fun fromCursor( cursor: Cursor): Marcas{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posName = cursor.getColumnIndex(TableMarcas.NAME)

            val id = cursor.getLong(posId)
            val name = cursor.getString(posName)

            return Marcas(id,name)
        }
    }
}