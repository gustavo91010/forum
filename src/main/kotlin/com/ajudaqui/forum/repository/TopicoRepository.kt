package com.ajudaqui.forum.repository

import com.ajudaqui.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository: JpaRepository<Topico, Long> {
}