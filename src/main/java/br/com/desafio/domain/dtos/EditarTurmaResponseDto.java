package br.com.desafio.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarTurmaResponseDto {
	private Long id_turma;
	private String numeroTurma;
	private String anoLetivo;
}
