package com.ajudaqui.forum.service

import com.ajudaqui.forum.exception.NotFoundException
import com.ajudaqui.forum.model.Usuario
import com.ajudaqui.forum.dto.NovoUsuarioForm
import com.ajudaqui.forum.repository.UsuarioRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository) {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(UsuarioService::class.java)
    }

    fun buscarPorId(autorId: Long): Usuario {

        logger.info("Buscando usuário com autorId: $autorId")
        return repository.findById(autorId).orElseThrow { NotFoundException("Usuário não encontrado") }
    }
    fun buscarTodos():MutableList<Usuario>{
        return repository.findAll()
    }

    fun cadastrar(form: NovoUsuarioForm): Usuario {
        return repository.save(
            Usuario(
            nome = form.nome,
            email = form.email
        )
        )

    }



}