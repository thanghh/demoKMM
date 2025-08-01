package org.oto.theory

import android.app.Application
import org.oto.theory.di.androidAppModule
import org.oto.theory.di.appModule
import org.oto.theory.di.commonModule
import org.oto.theory.di.initKoin
//import org.oto.theory.db.DatabaseManager

class OtoTheoryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(listOf(commonModule(this@OtoTheoryApplication)))
//        DatabaseManager.init()
    }
}