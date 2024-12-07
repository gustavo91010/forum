package com.ajudaqui.forum.controller

import com.ajudaqui.forum.dto.NovoTopicoForm
import com.ajudaqui.forum.model.AtualizacaoTopicoForm
import com.ajudaqui.forum.service.TopicosService
import com.ajudaqui.forum.view.TopicoView
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topicos")
class TopicoController(private val topicosService: TopicosService) {



    @GetMapping
    fun listar(): List<TopicoView> {
        return topicosService.getTopicos()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView{

        return topicosService.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid form:NovoTopicoForm){
        print("ha!")
        return topicosService.cadastrar(form)

    }

    @PutMapping
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm){
        return topicosService.atualizar(form)

    }
}