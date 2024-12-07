package com.ajudaqui.forum.model

import java.time.LocalDateTime

class Resposta (
    val id: Long?=null,
    val mensagem: String,
    val dataCracao: LocalDateTime= LocalDateTime.now(),
    val autor: Usuario,
    val tipoco: Topico,
    val solucao: Boolean
)
