package br.com.desafio.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.domain.dtos.request.turma.CadastrarTurmaRequestDto;
import br.com.desafio.domain.dtos.request.turma.EditarTurmaRequestDto;
import br.com.desafio.domain.dtos.response.turma.CadastrarTurmaResponseDto;
import br.com.desafio.domain.dtos.response.turma.ConsultarTurmaResponseDto;
import br.com.desafio.domain.dtos.response.turma.DeletarTurmaResponseDto;
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

	@PutMapping("editar")
	public ResponseEntity<?> put(@RequestBody EditarTurmaRequestDto request) {
		var response = turmaService.editarTurma(request);
		return ResponseEntity.ok(response);
	}

	@GetMapping("consultar/{numeroTurma}")
	public ResponseEntity<ConsultarTurmaResponseDto> get(@PathVariable String numeroTurma) {
		var response = turmaService.consultarTurma(numeroTurma);
		return ResponseEntity.ok(response);
	}

	
	 @DeleteMapping("deletar/{numeroTurma}") 
	 public ResponseEntity<DeletarTurmaResponseDto> delete(@PathVariable String numeroTurma){ 
		 var response = turmaService.deletarTurma(numeroTurma);
		 	return ResponseEntity.ok(response); 
	}	 
}
