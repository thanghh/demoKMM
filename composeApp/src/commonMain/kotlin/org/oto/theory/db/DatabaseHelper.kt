package org.oto.theory.db

import app.cash.sqldelight.db.SqlDriver
import org.oto.theory.AppDatabase

class DatabaseHelper(factory: DatabaseDriverFactory) {
    private val driver: SqlDriver = factory.createDriver()
    val database: AppDatabase = AppDatabase(driver)

    fun clearDatabase() {
    }

//    fun insertQuestion(id: Int, text: String, hasImage: Boolean) {
//        database.
//        database.insertQuestion(
//            id = id,
//            text = text,
//            has_image = if (hasImage) 1 else 0
//        )
//    }
//
//    fun getAllQuestions(): List<Question> {
//        return database.questionsQueries.selectAllQuestions().executeAsList()
//    }
}
