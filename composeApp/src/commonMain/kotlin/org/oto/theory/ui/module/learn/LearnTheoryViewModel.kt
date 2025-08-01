package org.oto.theory.ui.module.learn

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


class LearnTheoryViewModel(
    val database: Database
) : ViewModel(), KoinComponent {
    private val _stateLearn: MutableStateFlow<LearnTheoryState> =
        MutableStateFlow(LearnTheoryState())
    val stateLearn: StateFlow<LearnTheoryState> = _stateLearn

    init {
        getData(
            onResult = { data ->
                _stateLearn.update {
                    it.copy(
                        listQuestion = data
                    )
                }
            }
        )
    }

    fun getData(onResult: (List<Question>) -> Unit) {
        viewModelScope.launch {
            try {
                val list = database.getAllQuestions().map {
                    val answers = database.getAnswersWithQuestionId(it.id)
                    Question(
                        id = it.id,
                        questionNumber = it.id.toInt(),
                        text = it.text,
                        answers = answers
                    )
                }
                println("question: ${list}")

                onResult(list)
            } catch (e: Exception) {
                println("Error fetching list: ${e.message}")
            }
        }
    }

}

data class LearnTheoryState(
    val listQuestion: List<Question> = listOf()
) {
    val chapter1 : List<Question> get() {
        return listQuestion.subList(0, 180)
    } // CHƯƠNG I. QUY ĐỊNH CHUNG VÀ QUY TẮC GIAO THÔNG ĐƯỜNG BỘ

    val chapter2 : List<Question> get() {
        return listQuestion.subList(180, 205)
    } // CHƯƠNG II. VĂN HÓA GIAO THÔNG, ĐẠO ĐỨC NGƯỜI LÁI XE, KỸ NĂNG PHÒNG CHÁY, CHỮA CHÁY VÀ CỨU HỘ, CỨU NẠN

    val chapter3 : List<Question> get() {
        return listQuestion.subList(205, 263)
    } // CHƯƠNG III. KỸ THUẬT LÁI XE

    val chapter4 : List<Question> get() {
        return listQuestion.subList(263, 300)
    } // CHƯƠNG IV. CẤU TẠO VÀ SỬA CHỮA

    val chapter5 : List<Question> get() {
        return listQuestion.subList(300, 485)
    } // CHƯƠNG V. BÁO HIỆU ĐƯỜNG BỘ

    val chapter6 : List<Question> get() {
        return listQuestion.subList(485, 600)
    } // CHƯƠNG VI. GIẢI THẾ SA HÌNH VÀ KỸ NĂNG XỬ LÝ TÌNH HUỐNG GIAO THÔNG
}