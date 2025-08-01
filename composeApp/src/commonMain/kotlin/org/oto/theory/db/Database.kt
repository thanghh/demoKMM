package org.oto.theory.db

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import org.oto.theory.AppDatabase
import org.oto.theory.QUESTIONS
import org.oto.theory.model.Answer
import org.oto.theory.model.Question
import kotlin.uuid.Uuid


//object Database {
//    private val config = RealmConfiguration.Builder(schema = setOf(Question::class))
//        .name("questions.realm")
//        .build()
//
//    val realm: Realm by lazy {
//        Realm.open(config)
//    }
//
//    suspend fun addQuestion(question: Question) {
//        Database.realm.write {
//            copyToRealm(question)
//        }
//    }
//
//    fun getQuestions(): List<Question> {
//        return Database.realm.query<Question>().find()
//    }
//}

//object Database {
//    private lateinit var driver: SqlDriver
//    lateinit var db: AppDatabase
//
//    fun initialize(driver: SqlDriver) {
//        this.driver = driver
//        db = AppDatabase(driver)
//    }
//}

class Database(databaseDriverFactory: SqlDriver) {

    private val database = AppDatabase(databaseDriverFactory)
    private val dbQuery = database.questionsQueries

    internal fun getAllQuestions(): List<QUESTIONS> {
        return dbQuery.selectAllQuestions().executeAsList()
    }

    internal fun getAnswersWithQuestionId(questionId: Long): List<Answer> {
        return dbQuery.selectAnswersByQuestionId(questionId).executeAsList().map { row ->
            Answer(
                id = row.id.toInt(),
                questionId = row.question_id.toInt(),
                text = row.text,
                isCorrect = row.is_correct.toInt() == 1
            )

        }
    }

    internal fun insertQuestion(question: Question) {
         dbQuery.insertOrReplaceQuestion(
             id = question.id,
             question_number = question.id?.toLong() ?: 0,
             text = question.text,
             image = question.image
         )
    }

    internal fun insertAnswers(questionId: Int, answer: Answer) {
        dbQuery.insertOrReplaceAnswers(
            id = "$questionId${answer.id}".toLongOrNull(),
            question_id = questionId.toLong(),
            text = answer.text,
            is_correct = if(answer.isCorrect) 1 else 0
        )
    }

    private fun mapQuestions(
         id: Long,
         questionNumber: Long,
         text: String,
         image: String?,
    ): Question {
        return Question(
            id = id,
            questionNumber = questionNumber.toInt(),
            text = text,
            image = image?:""
        )
    }
}