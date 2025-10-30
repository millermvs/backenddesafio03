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

import br.com.desafio.domain.dtos.CadastrarAlunoRequestDto;
import br.com.desafio.domain.dtos.CadastrarAlunoResponseDto;
import br.com.desafio.domain.dtos.ConsultarAlunoResponseDto;
import br.com.desafio.domain.dtos.DeletarAlunoResponseDto;
import br.com.desafio.domain.dtos.EditarAlunoRequestDto;
import br.com.desafio.domain.dtos.EditarAlunoResponseDto;
import br.com.desafio.domain.services.AlunoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/alunos")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@PostMapping("cadastrar")
	public ResponseEntity<CadastrarAlunoResponseDto> post(@Valid @RequestBody CadastrarAlunoRequestDto request) {
		var response = alunoService.cadastrarAluno(request);
		return ResponseEntity.ok(response);
	}

	@PutMapping("editar")
	public ResponseEntity<EditarAlunoResponseDto> put(@RequestBody EditarAlunoRequestDto request) {
		var response = alunoService.editarAluno(request);
		return ResponseEntity.ok(response);
	}

	@GetMapping("consultar/{cpf}")
	public ResponseEntity<ConsultarAlunoResponseDto> get(@PathVariable String cpf) {
		var response = alunoService.consultarAluno(cpf);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("deletar/{id}")
	public ResponseEntity<DeletarAlunoResponseDto> delete(@PathVariable Long id) {
		var response = alunoService.deletarAluno(id);
		return ResponseEntity.ok(response);

	}
}
