package com.example.finalproject

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

abstract class TabelaBD (val db: SQLiteDatabase, val nome: String) {
    abstract fun cria()

    fun insert(values: ContentValues) =
        db.insert(
            nome,
            null,
            values)//paramento para inserir numeros a null, mas isso nuca a contece entao pomos null pq nao usamos

    fun update(values: ContentValues, whereClaus: String, whereArgs: Array<String>) =
        db.update(
            nome,
            values,
            whereClaus,
            whereArgs) //criar parametros

    fun delete(values: ContentValues, whereClause: String, whereArgs: Array<String>) =
        db.delete(
            nome,
            whereClause,
            whereArgs )

    fun query(
        columns: Array<String>,
        selection: String,
        selectionArgs: Array<String>,
        groupBy: String,
        having: String,
        orderBy: String
    ) = db.query(nome,columns,selection,selectionArgs,groupBy,having,orderBy)
}