package com.ajudaqui.forum.service

import com.ajudaqui.forum.dto.NovoTopicoForm
import com.ajudaqui.forum.exception.NotFoundException
import com.ajudaqui.forum.model.AtualizacaoTopicoForm
import com.ajudaqui.forum.model.Topico
import com.ajudaqui.forum.view.TopicoView
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.NoSuchElementException


@Service
class TopicosService(
    private var topicos: MutableList<Topico> = mutableListOf(),
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
) {


    fun getTopicos(): List<TopicoView> {

        return topicos.stream().map { t -> toTopicList(t) }.collect(Collectors.toList())
    }

    private fun toTopicList(topico: Topico): TopicoView {
        return TopicoView(
            id = topico.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            dataCriacao = topico.dataCriacao,
            status = topico.status
        )
    }

    fun buscarPorId(id: Long): TopicoView {
        var topico = topicos.find { it.id == id } ?: throw NotFoundException("Topico com Id não localizado")

        return toTopicList(topico);
    }

    fun cadastrar(dto: NovoTopicoForm): TopicoView {

        var tipoco = Topico(
            id = topicos.size.toLong(),
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            curso = cursoService.buscarPorId(dto.cursoId),
            autor = usuarioService.buscarPorId(dto.autorId)
        )
        topicos.add(
            tipoco
        )
        return toTopicList(tipoco)

    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        // Pegar o index do elemento que tenha o id solicitado
        val topicoIndex = topicos.indexOfFirst { it.id == form.id }
        if (topicoIndex == -1) {
            throw NotFoundException("Tópico com ID ${form.id} não encontrado.")
        }
        val topico = topicos[topicoIndex]

        val topicoAtualizado = topico.copy(
            titulo = if (form.titulo.isNullOrBlank()) topico.titulo else form.titulo,
            mensagem = if (form.mensagem.isNullOrBlank()) topico.mensagem else form.mensagem
        )
        topicos[topicoIndex] = topicoAtualizado

        return toTopicList(topicoAtualizado)
    }

    fun deletar(id: Long) {

        var topico = topicos.find { it.id == id } ?: throw NoSuchElementException("Id $id não localizado.")

        /*
        topicos.stream()
            .filter{
                t-> t.id == id
            }.findFirst().orElseThrow { NoSuchElementException("Id $id não localizado.") }
         */

        topicos.remove(topico)
    }


}