package br.com.desafio.domain.exceptions;

public class AlunoJaCadastradoNestaTurmaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Aluno já cadastrado nesta turma.";
	}

}
