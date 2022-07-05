package com.example.finalproject

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

class ContentProviderProdutos : ContentProvider() {
    var dbOpenHelper : BDCompararPrecoOpenHelper? = null

    override fun onCreate(): Boolean{
        dbOpenHelper = BDCompararPrecoOpenHelper(context)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db = dbOpenHelper!!.readableDatabase

        requireNotNull(projection)
        val colunas = projection as Array<String>

        val argsSeleccao = selectionArgs as Array<String>?

        val id = uri.lastPathSegment

        val cursor = when (getUriMatcher().match(uri)){
            URI_PRODUTO-> TableProdutos(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_PRODUTO_ESPECIFICA -> TableProdutos(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_LOJA -> TableLoja(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_LOJA_ESPECIFICO -> TableLoja(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_MARCA -> TableLoja(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_MARCA_ESPECIFICO -> TableMarcas(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            else -> null
        }
        return  cursor
    }

    override fun getType(uri: Uri): String? =
        when (getUriMatcher().match(uri)) {
            URI_PRODUTO -> "$MULTIPLOS_REGISTOS/${TableProdutos.NAME}"
            URI_PRODUTO_ESPECIFICA -> "$UNICO_REGISTO/${TableProdutos.NAME}"
            URI_LOJA -> "$MULTIPLOS_REGISTOS/${TableLoja.NAME}"
            URI_LOJA_ESPECIFICO -> "$UNICO_REGISTO/${TableLoja.NAME}"
            URI_MARCA -> "$MULTIPLOS_REGISTOS/${TableMarcas.NOME}"
            URI_MARCA_ESPECIFICO -> "$UNICO_REGISTO/${TableMarcas.NOME}"

            else -> null
        }


    override fun insert (uri: Uri, values: ContentValues?): Uri? {
        val db = dbOpenHelper!!.writableDatabase

        requireNotNull(values)

        val id = when (getUriMatcher().match(uri)) {
            URI_PRODUTO -> TableProdutos(db).insert(values)
            URI_LOJA -> TableLoja(db).insert(values)
            URI_MARCA -> TableMarcas(db).insert(values)
            else -> -1
        }

        db.close()

        if (id == -1L) return null

        return Uri.withAppendedPath(uri, "$id")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = dbOpenHelper!!.writableDatabase

        val id = uri.lastPathSegment

        val registosApagados = when (getUriMatcher().match(uri)) {
            URI_PRODUTO_ESPECIFICA -> TableProdutos(db).delete("${BaseColumns._ID}", arrayOf("${id}"))
            URI_LOJA_ESPECIFICO -> TableProdutos(db).delete("${BaseColumns._ID}", arrayOf("${id}"))
            URI_MARCA_ESPECIFICO -> TableMarcas(db).delete("${BaseColumns._ID}", arrayOf("${id}"))
            else -> 0
        }

        db.close()

        return  registosApagados
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        requireNotNull(values)

        val db = dbOpenHelper!!.writableDatabase

        val id = uri.lastPathSegment

        val registosAlterados = when (getUriMatcher().match(uri)) {
            URI_PRODUTO_ESPECIFICA -> TableProdutos(db).update(values, "${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_LOJA_ESPECIFICO -> TableLoja(db).update(values,"${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_MARCA_ESPECIFICO -> TableMarcas(db).update(values,"${BaseColumns._ID}=?", arrayOf("${id}"))
            else -> 0
        }

        db.close()

        return registosAlterados
    }

    companion object {
        private const val AUTHORITY = "pt.ipg.livros"

        private const val URI_PRODUTO = 100
        private const val URI_PRODUTO_ESPECIFICA = 101
        private const val URI_LOJA = 200
        private const val URI_LOJA_ESPECIFICO = 201
        private const val URI_MARCA = 300
        private const val URI_MARCA_ESPECIFICO = 301

        private const val UNICO_REGISTO = "vnd.android.cursor.item"
        private const val MULTIPLOS_REGISTOS = "vnd.android.cursor.dir"

        private val ENDERECO_BASE = Uri.parse("content://$AUTHORITY")
        val ENDERECO_PRODUTO = Uri.withAppendedPath(ENDERECO_BASE, TableProdutos.NAME)
        val ENDERECO_LOJA = Uri.withAppendedPath(ENDERECO_BASE, TableLoja.NAME)
        val ENDERECO_MARCA = Uri.withAppendedPath(ENDERECO_BASE, TableMarcas.NOME)


        fun getUriMatcher() : UriMatcher {
            var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTHORITY, TableProdutos.NAME, URI_PRODUTO)
            uriMatcher.addURI(AUTHORITY, "${TableProdutos.NAME}/#", URI_PRODUTO_ESPECIFICA)
            uriMatcher.addURI(AUTHORITY, TableLoja.NAME, URI_LOJA)
            uriMatcher.addURI(AUTHORITY, "${TableLoja.NAME}/#", URI_LOJA_ESPECIFICO)
            uriMatcher.addURI(AUTHORITY, TableMarcas.NOME, URI_MARCA)
            uriMatcher.addURI(AUTHORITY, "${TableMarcas.NOME}/#", URI_MARCA_ESPECIFICO)

            return uriMatcher
        }
    }
}