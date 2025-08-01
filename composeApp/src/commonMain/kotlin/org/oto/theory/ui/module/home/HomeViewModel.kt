package org.oto.theory.ui.module.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.utils.io.core.String
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.oto.theory.model.Question
import org.oto.theory.model.QuestionsResponse
import kotlinproject.composeapp.generated.resources.Res
import org.koin.compose.koinInject
import org.koin.core.component.inject
import org.oto.theory.AppDatabase
import org.oto.theory.db.Database
import org.oto.theory.db.DatabaseDriverFactory
import org.oto.theory.db.DatabaseHelper
import org.oto.theory.repository.MangaRepository

//import org.oto.theory.common.db.Database

class HomeViewModel(
    val database: Database
) : ViewModel(), KoinComponent {
    private val _stateHome: MutableStateFlow<HomeState> =
        MutableStateFlow(HomeState())
    val stateHome: StateFlow<HomeState> = _stateHome
//    private val factory: DatabaseDriverFactory by inject()

    init {
        if(database.getAllQuestions().isEmpty()) {
            getData(
                onResult = { data ->
                    _stateHome.update {
                        it.copy(
                            listQuestion = data
                        )
                    }
                }
            )
        }
    }

    fun getData(onResult: (List<Question>) -> Unit) {
        viewModelScope.launch {
            try {
                val questions = loadQuestionsFromFile()
                questions.questions.forEach { question ->
                    database.insertQuestion(
                        Question(
                            id = question.id,
                            questionNumber = question.id?.toInt() ?: 0,
                            text = question.text,
                            image = question.image
                        )
                    )
                    if(question.answers.isNotEmpty()){
                        question.answers.forEach { answer ->
                            database.insertAnswers(
                                questionId = question.id?.toInt() ?:0,
                                answer = answer
                            )
                        }
                    }
                }

                onResult(listOf())
            } catch (e: Exception) {
                println("Error fetching list: ${e.message}")
            }
        }
    }

//    fun loadJsonFromResources(resourceName: String): String {
//        val resourceStream = {}::class.java.getResourceAsStream("/$resourceName")
//            ?: throw IllegalArgumentException("Resource not found: $resourceName")
//        return resourceStream.bufferedReader().use { it.readText() }
//    }
//
//    fun loadQuestions(): QuestionsResponse {
//        val jsonString = loadJsonFromResources("questions.json")
//        return Json.decodeFromString(jsonString)
//    }

    private suspend fun loadQuestionsFromFile(): QuestionsResponse {
        val readBytes = Res.readBytes("files/questions.json")
        val jsonString = String(readBytes)

        return Json.decodeFromString(jsonString)
    }


}

data class HomeState(
    val listQuestion: List<Question> = listOf()
)