package org.oto.theory.di

import app.cash.sqldelight.db.SqlDriver
import org.oto.theory.network.APIClient
import org.oto.theory.repository.MangaRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.oto.theory.AppDatabase
import org.oto.theory.db.Database
import org.oto.theory.db.DatabaseDriverFactory
import org.oto.theory.ui.module.home.HomeViewModel
import org.oto.theory.ui.module.learn.LearnTheoryViewModel

val appModule: Module = module {
    single { APIClient() }
}

//val commonModule = module {
//    single { AppDatabase(get()) }
//    single { Database(get()) }
//    singleOf(::HomeViewModel)
//}

