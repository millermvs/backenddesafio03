package br.com.desafio.domain.exceptions;

public class TurmaNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Turma não encontrada.";
	}

}
