package br.com.desafio.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.domain.dtos.CadastrarTurmaRequestDto;
import br.com.desafio.domain.dtos.CadastrarTurmaResponseDto;
import br.com.desafio.domain.services.TurmaService;

@RestController
@RequestMapping("/api/v1/turmas")
public class TurmaController {

	@Autowired
	private TurmaService turmaService;

	@PostMapping("cadastrar")
	public ResponseEntity<CadastrarTurmaResponseDto> post(@RequestBody CadastrarTurmaRequestDto request) {
		var response = turmaService.cadastrarTurma(request);
		return ResponseEntity.ok(response);
	}
}
