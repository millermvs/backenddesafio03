package br.com.desafio.domain.exceptions;

public class AlunoNaoPodeSerExcluidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Aluno cadastrado em uma turma, não pode ser excluído.";
	}
}
