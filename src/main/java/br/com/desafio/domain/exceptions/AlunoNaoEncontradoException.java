package br.com.desafio.domain.exceptions;

public class AlunoNaoEncontradoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Aluno não encontrado.";
	}
	
}
