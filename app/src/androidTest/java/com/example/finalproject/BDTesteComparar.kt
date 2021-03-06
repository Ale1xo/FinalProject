package com.example.finalproject

import android.database.sqlite.SQLiteDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BDTesteComparar {
    private fun appContext() =
        InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BDCompararPrecoOpenHelper(appContext())
        return openHelper.writableDatabase
    }

    private fun insereMarca(db: SQLiteDatabase, marca: Marcas) {
        marca.id = TableMarcas(db).insert(marca.toContentValues())
        assertNotEquals(-1, marca.id)
    }

    private fun insereAvaliacao(db: SQLiteDatabase, avaliacao: Avaliacao) {
        avaliacao.id = TableMarcas(db).insert(avaliacao.toContentValues())
        assertNotEquals(-1, avaliacao.id)
    }

    private fun insereProduto(db: SQLiteDatabase, produto: Produtos) {
        produto.id = TableProdutos(db).insert(produto.toContentValues())
        assertNotEquals(-1, produto.id)
    }

    private fun insereLoja(db: SQLiteDatabase, loja: Loja) {
        loja.id = TableLoja(db).insert((loja.toContentValues()))
        assertNotEquals(-1, loja.id)
    }

    @Before
    fun apagaBaseDados() {
        appContext().deleteDatabase(BDCompararPrecoOpenHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados() {
        val openHelper = BDCompararPrecoOpenHelper(appContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)
        db.close()
    }

    @Test
    fun consegueInserirMarca() {
        val db = getWritableDatabase()
        insereMarca(db, Marcas(1, "Apple"))

        db.close()
    }

    @Test
    fun consegueInserirAvaliacao() {
        val db = getWritableDatabase()
        insereAvaliacao(db, Avaliacao(-1, 8, 6))
    }

    @Test
    fun consegueInserirProduto() {
        val db = getWritableDatabase()
        insereProduto(db, Produtos(-1, "Iphone", 1200, 54))
    }

    @Test
    fun consegueInserirLoja() {
        val db = getWritableDatabase()
        insereLoja(db, Loja(-1, "iStore", "Lisboa"))
    }

    @Test
    fun consegueAlterarMarca() {
        val db = getWritableDatabase()

        val marca = Marcas(-1, "Teste")
        insereMarca(db, marca)

        marca.name = "Samsung"

        val registosAlterados = TableMarcas(db).update(
            marca.toContentValues(),
            "${TableMarcas.IDMARCA}=?",
            arrayOf("${marca.id}")
        )

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun connsegueEliminarMarca() {
        val db = getWritableDatabase()

        val marca = Marcas(-1, "Teste")
        insereMarca(db, marca)

        val registosEliminados = TableMarcas(db).delete(
            marca.toContentValues(),
            "${TableMarcas.IDMARCA}=?",
            arrayOf("${marca.id}")
        )

        assertEquals(1, registosEliminados)
        db.close()
    }
}
