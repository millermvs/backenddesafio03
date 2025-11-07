package br.com.desafio.domain.dtos.response.turma;

import java.util.List;

import br.com.desafio.domain.dtos.request.aluno.AlunoResumoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultarTurmaResponseDto {

	private Long id_turma;
	private String numeroTurma;
	private String anoLetivo;
	private List<AlunoResumoDto> alunos;

}
