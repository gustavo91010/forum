package com.ajudaqui.forum.service

import com.ajudaqui.forum.dto.NovoTopicoForm
import com.ajudaqui.forum.exception.NotFoundException
import com.ajudaqui.forum.model.AtualizacaoTopicoForm
import com.ajudaqui.forum.model.Topico
import com.ajudaqui.forum.repository.TopicoRepository
import com.ajudaqui.forum.view.TopicoView
import org.springframework.stereotype.Service
import java.util.stream.Collectors


@Service
class TopicosService(
    private var topicrepository: TopicoRepository,
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
) {


    fun getTopicos(nomeCurso: String?): List<TopicoView> {

        return if (nomeCurso.isNullOrEmpty()) {
            totoTopicViewList(topicrepository.findAll())
        } else {
            totoTopicViewList(topicrepository.findByCursoNome(nomeCurso))
        }

    }

    private fun totoTopicViewList(topicos: MutableList<Topico>): MutableList<TopicoView> {
        return topicos.stream()
            .map { toTopicView(it) }
            .collect(Collectors.toList())
    }

    private fun toTopicView(topico: Topico): TopicoView {
        return TopicoView(
            id = topico.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            dataCriacao = topico.dataCriacao,
            status = topico.status
        )
    }

    fun buscarPorId(id: Long): TopicoView {
        //var topico = topicos.find { it.id == id } ?: throw NotFoundException("Topico com Id não localizado")

        var topico = topicrepository.findById(id).orElseThrow { NotFoundException("Topico com Id não localizado") }
        return toTopicView(topico)
    }

    fun cadastrar(dto: NovoTopicoForm): TopicoView {

        var topico = Topico(
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            curso = cursoService.buscarPorId(dto.cursoId),
            autor = usuarioService.buscarPorId(dto.autorId)
        )

        return toTopicView(save(topico))

    }

    private fun save(topico: Topico): Topico {
        return topicrepository.save(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        // Pegar o index do elemento que tenha o id solicitado
        var topico = topicrepository.findById(form.id).orElseThrow { NotFoundException("Topico com Id não localizado") }


        val topicoAtualizado = topico.copy(
            titulo = if (form.titulo.isNullOrBlank()) topico.titulo else form.titulo,
            mensagem = if (form.mensagem.isNullOrBlank()) topico.mensagem else form.mensagem
        )
        return toTopicView(
            save(topicoAtualizado)
        )
    }

    fun deletar(id: Long) {
        topicrepository.deleteById(id)

    }


}