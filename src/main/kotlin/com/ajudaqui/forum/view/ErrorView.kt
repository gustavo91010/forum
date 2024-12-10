package com.ajudaqui.forum.view

import java.time.LocalDateTime

data class ErrorView (
    val timestamp: LocalDateTime= LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)
