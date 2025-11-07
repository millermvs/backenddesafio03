package br.com.desafio.domain.dtos.response.turma;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletarTurmaResponseDto {
	
	private String numeroTurma;
	private String anoLetivo;
	private String resposta;

}
