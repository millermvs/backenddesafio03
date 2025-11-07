package br.com.desafio.domain.dtos.request.aluno;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAlunoToTurmaRequestDto {
	
	private Long idAluno;
	private String numeroTurma;

}
