package com.ajudaqui.forum.service

import com.ajudaqui.forum.dto.NovoTopicoForm
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

        return topicos.stream().map { t ->toTopicList(t) }.collect(Collectors.toList())
    }

    private fun toTopicList(topico: Topico):TopicoView{
       return TopicoView(
           id = topico.id,
           titulo = topico.titulo,
           mensagem = topico.mensagem,
           dataCriacao = topico.dataCriacao,
           status = topico.status )
    }
    fun buscarPorId(id: Long): TopicoView {
        var topico=topicos.find { it.id == id } ?: throw NoSuchElementException("Topico com Id não localizado")

        return toTopicList(topico);
    }

    fun cadastrar(dto: NovoTopicoForm) {

        println("NovoTopicoDto "+ dto.toString())
        topicos.add(
            Topico(
                id = topicos.size.toLong(),
                titulo = dto.titulo,
                mensagem = dto.mensagem,
                curso = cursoService.buscarPorId(dto.cursoId),
                autor = usuarioService.buscarPorId(dto.autorId)
            )
        )

        print("Total de topicos: "+topicos.size)
    }


}