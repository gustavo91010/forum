package com.ajudaqui.forum.controller

import com.ajudaqui.forum.dto.NovoUsuarioForm
import com.ajudaqui.forum.model.*
import com.ajudaqui.forum.service.UsuarioService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/usuario")
class UsuarioController(private val topicosService: UsuarioService) {


    @GetMapping
    fun listar(): MutableList<Usuario> {
        return topicosService.buscarTodos()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): Usuario {

        return topicosService.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid form: NovoUsuarioForm,
        uriCom: UriComponentsBuilder
    ): ResponseEntity<Usuario> {
        // UriComponentsBuilder pego o endereço completo do path
        var topicoView = topicosService.cadastrar(form)
        val uri = uriCom.path("/topicos/${topicoView.id}").build().toUri()

        return ResponseEntity.created(uri).body(topicoView)

    }

}