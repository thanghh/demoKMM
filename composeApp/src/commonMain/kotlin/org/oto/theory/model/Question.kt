package org.oto.theory.model


import kotlinx.serialization.Serializable

//class Question : RealmObject {
//    @PrimaryKey
//    val id: Int = 0
//    val text: String = ""
//    val image: String ?= ""
//    val answers: RealmList<Answer> = realmListOf()
//}
@Serializable
data class Question (
    val id: Long? = 0,
    val questionNumber: Int = 0,
    val text: String = "",
    val image: String ?= "",
    val answers: List<Answer> = listOf(),
) {

}