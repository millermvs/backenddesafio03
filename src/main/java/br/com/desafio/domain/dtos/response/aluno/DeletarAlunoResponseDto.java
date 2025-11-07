package br.com.desafio.domain.dtos.response.aluno;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletarAlunoResponseDto {
	
	private String nome;	
	private String cpf;	
	private String email;
	private String reposta;

}
