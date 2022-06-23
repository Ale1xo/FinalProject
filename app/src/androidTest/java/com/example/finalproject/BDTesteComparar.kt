package com.example.finalproject

import android.database.sqlite.SQLiteDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BDTesteComparar {
    private fun appContext() =
        InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase():SQLiteDatabase{
        val openHelper = BDCompararPrecoOpenHelper(appContext())
        return  openHelper.writableDatabase
    }

    @Before
    fun apagaBaseDados(){
        appContext().deleteDatabase(BDCompararPrecoOpenHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados(){
        val openHelper = BDCompararPrecoOpenHelper(appContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)
        db.close()
    }


}