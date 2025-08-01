package org.oto.theory.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.oto.theory.AppDatabase
import org.oto.theory.AppDatabase.Companion.invoke
import org.oto.theory.db.AndroidDatabaseDriverFactory
import org.oto.theory.db.Database
import org.oto.theory.ui.module.home.HomeViewModel
import org.oto.theory.ui.module.learn.LearnTheoryViewModel

fun androidAppModule(context: Context): Module = module {
    single { AndroidDatabaseDriverFactory(
        context = context
    )
    }
}

fun commonModule(context: Context) = module {
    single<SqlDriver> { createDriver(context) }
    single { AppDatabase(get()) }
    single { Database(get()) }
    singleOf(::HomeViewModel)
    singleOf(::LearnTheoryViewModel)

}

fun createDriver(context: Context): SqlDriver {
    return AndroidSqliteDriver(AppDatabase.Schema, context, "QUESTIONS.db")
}