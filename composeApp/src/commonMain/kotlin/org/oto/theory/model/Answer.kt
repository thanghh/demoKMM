package org.oto.theory.model

import kotlinx.serialization.Serializable

@Serializable
data class Answer (
    val id: Int = 0,
    val questionId: Int = 0,
    val text: String = "",
    val isCorrect: Boolean = false,
)


//class Answer : RealmObject {
//    @PrimaryKey
//    val id: Int = 0
//    val text: String = ""
//    val isCorrect: Boolean = false
//}