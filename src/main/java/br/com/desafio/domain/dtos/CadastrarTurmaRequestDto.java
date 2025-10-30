package br.com.desafio.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarTurmaRequestDto {
	
	private String numeroTurma;
	private String anoLetivo;

}
