package com.ajudaqui.forum.service

import com.ajudaqui.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class UsuarioService(var usuarios: List<Usuario>) {
    init {
        var usuario = Usuario(
            id = 657,
            email = "bob@email.com",
            nome = "bob"
        )
        usuarios= Arrays.asList(usuario)
    }

    fun buscarPorId(autorId: Long): Usuario {

        println("Buscando usuário com autorId: $autorId")
        return usuarios.find { it.id == autorId } ?: throw NoSuchElementException("Usuario com Id não localizado")
    }




}