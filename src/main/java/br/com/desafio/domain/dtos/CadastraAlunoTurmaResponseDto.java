package br.com.desafio.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastraAlunoTurmaResponseDto {

	private Long idAluno;
	private String nomeAluno;
	private Long idTurma;
	private String numeroTurma;
	private String resposta;
	
}
