package br.com.desafio.domain.handlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.desafio.domain.exceptions.AlunoNaoEncontradoException;

@ControllerAdvice
public class AlunoNaoEncontradoHandler {

	@ExceptionHandler(AlunoNaoEncontradoException.class)
	public ResponseEntity<Object> handlerAlunoNaoEncontrado(AlunoNaoEncontradoException ex) {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("datahora", LocalDateTime.now());
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("errors", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(body);
	}

}
