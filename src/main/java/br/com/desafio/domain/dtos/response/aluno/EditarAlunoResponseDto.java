package br.com.desafio.domain.dtos.response.aluno;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditarAlunoResponseDto {

	private String nome;	
	private String cpf;	
	private String email;
	private Long numeroTurma;
	
}
