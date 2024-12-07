package com.ajudaqui.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NovoTopicoForm(

    /* para garantir que as vadidaçõesfuncionem no campo do fild... tem que por o field antes...
    parece que elas funcionand naturalmente nos metdoso get e set...
    * */
    @field:NotEmpty @field:Size(min=5, max = 100)
    var titulo: String,
    @field:NotEmpty    var mensagem: String,
    @field:NotNull var cursoId: Long,
    @field:NotNull val autorId: Long)