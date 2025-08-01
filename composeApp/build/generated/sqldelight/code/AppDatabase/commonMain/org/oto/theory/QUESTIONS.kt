package org.oto.theory

import kotlin.Long
import kotlin.String

public data class QUESTIONS(
  public val id: Long,
  public val question_number: Long,
  public val text: String,
  public val image: String?,
)
