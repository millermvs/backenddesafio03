package br.com.desafio.domain.dtos.response.aluno;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarAlunoResponseDto {
	
	private Long id;
	private String nome;
	private String email;
	private String turma;

}
