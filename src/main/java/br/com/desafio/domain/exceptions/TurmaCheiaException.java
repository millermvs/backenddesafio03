package br.com.desafio.domain.exceptions;

public class TurmaCheiaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Turma está cheia.";
	}

}
