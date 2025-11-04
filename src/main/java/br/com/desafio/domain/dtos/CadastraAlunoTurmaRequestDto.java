package br.com.desafio.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastraAlunoTurmaRequestDto {
	
	private Long idAluno;
	private String numeroTurma;

}
