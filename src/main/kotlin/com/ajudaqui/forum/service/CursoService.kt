package com.ajudaqui.forum.service

import com.ajudaqui.forum.model.Curso
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(var cursos:List<Curso>) {
init{
    var curso= Curso(
        id = 1,
        nome = "Kotlin",
        categoria= "mobile"
    )

    cursos= Arrays.asList(curso)
}

    fun buscarPorId(id:Long): Curso{
        return cursos.stream().filter ({ c -> c.id== id }).findFirst().get()
    }

}