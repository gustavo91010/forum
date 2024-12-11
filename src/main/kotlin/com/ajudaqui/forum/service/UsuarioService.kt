package com.ajudaqui.forum.service

import com.ajudaqui.forum.exception.NotFoundException
import com.ajudaqui.forum.model.Usuario
import com.ajudaqui.forum.repository.UsuarioRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class UsuarioService(private val repository: UsuarioRepository) {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(UsuarioService::class.java)
    }

    fun buscarPorId(autorId: Long): Usuario {

        logger.info("Buscando usuário com autorId: $autorId")
        return repository.findById(autorId).orElseThrow { NotFoundException("Usuário não encontrado") }
    }




}