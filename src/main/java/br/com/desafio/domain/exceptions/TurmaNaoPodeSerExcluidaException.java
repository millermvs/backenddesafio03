package br.com.desafio.domain.exceptions;

public class TurmaNaoPodeSerExcluidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Turma contém alunos, não pode ser excluída.";
	}

}
