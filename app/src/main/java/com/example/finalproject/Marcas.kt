package com.example.finalproject

import android.content.ContentValues

class Marcas(var idMarca : Long, var name : String) {
    fun toContentValues(): ContentValues {
        val valoresMarca = ContentValues()
        valoresMarca.put(TableLoja.NAME, name)
        valoresMarca.put(TableLoja.IDLOJA, idMarca)
        return valoresMarca
    }
}