package org.oto.theory

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class QuestionsQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> selectAllQuestions(mapper: (
    id: Long,
    question_number: Long,
    text: String,
    image: String?,
  ) -> T): Query<T> = Query(-1_784_878_500, arrayOf("QUESTIONS"), driver, "Questions.sq",
      "selectAllQuestions",
      "SELECT QUESTIONS.id, QUESTIONS.question_number, QUESTIONS.text, QUESTIONS.image FROM QUESTIONS") {
      cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getLong(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)
    )
  }

  public fun selectAllQuestions(): Query<QUESTIONS> = selectAllQuestions { id, question_number,
      text, image ->
    QUESTIONS(
      id,
      question_number,
      text,
      image
    )
  }

  public fun <T : Any> selectAnswersByQuestionId(question_id: Long, mapper: (
    id: Long,
    question_id: Long,
    text: String,
    is_correct: Long,
  ) -> T): Query<T> = SelectAnswersByQuestionIdQuery(question_id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getLong(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!
    )
  }

  public fun selectAnswersByQuestionId(question_id: Long): Query<ANSWERS> =
      selectAnswersByQuestionId(question_id) { id, question_id_, text, is_correct ->
    ANSWERS(
      id,
      question_id_,
      text,
      is_correct
    )
  }

  /**
   * @return The number of rows updated.
   */
  public fun insertOrReplaceQuestion(
    id: Long?,
    question_number: Long,
    text: String,
    image: String?,
  ): QueryResult<Long> {
    val result = driver.execute(-1_469_678_966, """
        |INSERT OR REPLACE INTO QUESTIONS (id, question_number, text, image)
        |VALUES (?, ?, ?, ?)
        """.trimMargin(), 4) {
          bindLong(0, id)
          bindLong(1, question_number)
          bindString(2, text)
          bindString(3, image)
        }
    notifyQueries(-1_469_678_966) { emit ->
      emit("QUESTIONS")
    }
    return result
  }

  /**
   * @return The number of rows updated.
   */
  public fun insertOrReplaceAnswers(
    id: Long?,
    question_id: Long,
    text: String,
    is_correct: Long,
  ): QueryResult<Long> {
    val result = driver.execute(1_082_463_569, """
        |INSERT OR REPLACE INTO ANSWERS (id, question_id, text, is_correct)
        |VALUES (?, ?, ?, ?)
        """.trimMargin(), 4) {
          bindLong(0, id)
          bindLong(1, question_id)
          bindString(2, text)
          bindLong(3, is_correct)
        }
    notifyQueries(1_082_463_569) { emit ->
      emit("ANSWERS")
    }
    return result
  }

  private inner class SelectAnswersByQuestionIdQuery<out T : Any>(
    public val question_id: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("ANSWERS", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("ANSWERS", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(2_058_816_701,
        """SELECT ANSWERS.id, ANSWERS.question_id, ANSWERS.text, ANSWERS.is_correct FROM ANSWERS WHERE question_id = ?""",
        mapper, 1) {
      bindLong(0, question_id)
    }

    override fun toString(): String = "Questions.sq:selectAnswersByQuestionId"
  }
}
