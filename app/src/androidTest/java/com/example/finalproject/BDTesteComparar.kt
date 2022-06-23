package com.example.finalproject

import android.database.sqlite.SQLiteDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BDTesteComparar {
    private fun appContext() =
        InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun apagaBaseDados(){
        appContext().deleteDatabase(BDCompararPrecoOpenHelper.NOME)
    }


}