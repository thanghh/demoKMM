package org.oto.theory.model

import kotlinx.serialization.Serializable


@Serializable
data class QuestionsResponse(
    val questions: List<Question>
)
