package br.com.desafio.domain.handlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.desafio.domain.exceptions.AlunoNaoPodeSerExcluidoException;




@ControllerAdvice
public class AlunoNaoPodeSerExcluidoHandler {
	
	@ExceptionHandler(AlunoNaoPodeSerExcluidoException.class)
	public ResponseEntity<Object> handlerAlunoNaoPodeSerExcluido(AlunoNaoPodeSerExcluidoException ex){
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("datahora", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("errors", ex.getMessage());
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(body);
	}

}
