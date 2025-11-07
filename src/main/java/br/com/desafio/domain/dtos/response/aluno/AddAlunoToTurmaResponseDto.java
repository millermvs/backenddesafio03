package br.com.desafio.domain.dtos.response.aluno;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAlunoToTurmaResponseDto {

	private Long idAluno;
	private String nomeAluno;
	private Long idTurma;
	private String numeroTurma;
	private String resposta;
	
}