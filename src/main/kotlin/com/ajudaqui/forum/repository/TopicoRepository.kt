package com.ajudaqui.forum.repository

import com.ajudaqui.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository: JpaRepository<Topico, Long> {

    // pegando o nome do objeto curso que esta na classe topico
    fun findByCursoNome(nome:String): MutableList<Topico>
    fun findByCursoNome(nome: String, paginacao: Pageable): Page<Topico>
}