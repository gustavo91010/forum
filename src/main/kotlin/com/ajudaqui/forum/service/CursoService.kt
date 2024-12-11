package com.ajudaqui.forum.service

import com.ajudaqui.forum.exception.NotFoundException
import com.ajudaqui.forum.model.Curso
import com.ajudaqui.forum.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(private val repository: CursoRepository) {


    fun buscarPorId(id:Long): Curso{
        // return cursos.stream().filter ({ c -> c.id== id }).findFirst().get()
        return repository.findById(id).orElseThrow { NotFoundException("Curso não localizado") }
    }

}