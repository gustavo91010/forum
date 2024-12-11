package com.ajudaqui.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Topico (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime= LocalDateTime.now(),

    @ManyToOne
    val curso: Curso,

    @ManyToOne // um topico tem somente um autor
    val autor: Usuario,

    @Enumerated(value = EnumType.STRING)
    val status: StatusTopico= StatusTopico.NAO_RESPONDIDO,

    @OneToMany(mappedBy = "topico")
    // Um topico tem varias resposyas, e ele vai ser encontrado / mapeado do outro lado como topico
    val respostas: List<Resposta> = ArrayList()
)