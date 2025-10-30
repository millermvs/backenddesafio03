package br.com.desafio.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarAlunoRequestDto {
	
	private Long id;
	private String nome;	
	private String cpf;	
	private String email;

}
