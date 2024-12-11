package com.ajudaqui.forum.repository

import com.ajudaqui.forum.model.Topico
import com.ajudaqui.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
}