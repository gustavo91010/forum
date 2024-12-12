package com.ajudaqui.forum.service

import com.ajudaqui.forum.exception.NotFoundException
import com.ajudaqui.forum.model.Curso
import com.ajudaqui.forum.dto.NovoCursoForm
import com.ajudaqui.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(private val repository: CursoRepository) {

    fun buscarTodos():MutableList<Curso>{
        return repository.findAll()
    }
    fun buscarPorId(id:Long): Curso{
        // return cursos.stream().filter ({ c -> c.id== id }).findFirst().get()
        return repository.findById(id).orElseThrow { NotFoundException("Curso não localizado") }
    }

    fun cadastrar(form: NovoCursoForm): Curso{
        return repository.save(Curso(
            nome = form.nome,
            categoria = form.categoria
        ))

    }

}