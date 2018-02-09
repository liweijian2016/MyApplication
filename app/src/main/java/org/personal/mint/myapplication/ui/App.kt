package org.personal.mint.myapplication.ui

import android.app.Application
import org.personal.mint.myapplication.extensions.DelegatesExt

/**
 * @author lwj
 * @date 2018/2/8
 */
class App : Application() {
    companion object {
//        @SuppressLint("StaticFieldLeak")
//        private var instance: Application? = null
//        @Suppress("unused")
//        fun instance() = instance!!
        var instance: App by DelegatesExt.notNullSingleValue()
    }

//    val database: SQLiteOpenHelper by lazy {
//        MyDatabaseHelper(applicationContext)
//    }

    override fun onCreate() {
        super.onCreate()
        instance = this
//        val db = database.writableDatabase
    }
}