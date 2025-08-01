package org.oto.theory

import kotlin.Long
import kotlin.String

public data class ANSWERS(
  public val id: Long,
  public val question_id: Long,
  public val text: String,
  public val is_correct: Long,
)
