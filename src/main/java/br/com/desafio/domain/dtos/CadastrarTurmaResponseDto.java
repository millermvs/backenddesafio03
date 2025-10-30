package br.com.desafio.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarTurmaResponseDto {
	
	private Long id;
	private String numeroTurma;
	private String anoLetivo;
	

}
