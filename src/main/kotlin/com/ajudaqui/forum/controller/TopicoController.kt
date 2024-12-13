package com.ajudaqui.forum.controller

import com.ajudaqui.forum.dto.NovoTopicoForm
import com.ajudaqui.forum.model.AtualizacaoTopicoForm
import com.ajudaqui.forum.service.TopicosService
import com.ajudaqui.forum.view.TopicoView
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicoController(private val topicosService: TopicosService) {


    @GetMapping //required = false por que esse parametro é opcional
    // presta atenção no dominio da pagonação: import org.springframework.data.domain.Pageable
   @Cacheable("lista_topicos_cache")
    fun listar(@RequestParam(required = false)
               nomeCurso: String?,
               paginacao: Pageable): Page<TopicoView> {
        return topicosService.getTopicos(nomeCurso,paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {

        return topicosService.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid form: NovoTopicoForm,
        uriCom: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        // UriComponentsBuilder pego o endereço completo do path
        var topicoView = topicosService.cadastrar(form)
        val uri = uriCom.path("/topicos/${topicoView.id}").build().toUri()

        return ResponseEntity.created(uri).body(topicoView)

    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): ResponseEntity<TopicoView> {
        val topocoView = topicosService.atualizar(form)
        return ResponseEntity.ok(topocoView)

    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT) // se der certo, ele vai devolver o status
    fun deletar(@PathVariable id: Long): String {
        topicosService.deletar(id)
        return "Id $id removido com sucesso"

    }
}