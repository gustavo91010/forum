package com.ajudaqui.forum.repository

import com.ajudaqui.forum.model.Curso
import com.ajudaqui.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
}