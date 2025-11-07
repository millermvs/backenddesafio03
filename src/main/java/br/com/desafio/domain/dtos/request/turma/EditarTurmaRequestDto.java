package br.com.desafio.domain.dtos.request.turma;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarTurmaRequestDto {
	private Long id_turma;
	private String numeroTurma;
	private String anoLetivo;
}
