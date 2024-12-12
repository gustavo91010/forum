package com.ajudaqui.forum.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class Resposta (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,
    val mensagem: String,
    val dataCracao: LocalDateTime= LocalDateTime.now(),

    @ManyToOne // mutias respostas para um autor
    val autor: Usuario,

    @ManyToOne // mutias respostas para um topico
    val topico: Topico,
    val solucao: Boolean
)
