package org.oto.theory.db

import app.cash.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}
//class DatabaseFactory(private val dispatcher: CoroutineDispatcher = Dispatchers.Default) {
//
//    fun createDriver(): SqlDriver {
//        return when (getPlatform().name) {
//            "Android" -> AndroidSqliteDriver(AppDatabase.Schema, context, "shared_database.db")
//            "iOS" -> NativeSqliteDriver(AppDatabase.Schema, "shared_database.db")
//            else -> {
//                SqlDriver()
//            }
//        }
//    }
//
//    fun createDatabase(driver: SqlDriver): AppDatabase {
//        return AppDatabase(driver)
//    }
//}