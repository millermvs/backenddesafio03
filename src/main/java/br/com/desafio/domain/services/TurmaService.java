package br.com.desafio.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.domain.dtos.CadastrarTurmaRequestDto;
import br.com.desafio.domain.dtos.CadastrarTurmaResponseDto;
import br.com.desafio.domain.entities.Turma;
import br.com.desafio.infrastructure.repositories.TurmaRepository;

@Service
public class TurmaService {
	
	@Autowired
	private TurmaRepository turmaRepository;

	public CadastrarTurmaResponseDto cadastrarTurma(CadastrarTurmaRequestDto request) {
		
		var turma = new Turma();
		turma.setNumeroTurma(request.getNumeroTurma());
		turma.setAnoLetivo(request.getAnoLetivo());
		turmaRepository.save(turma);
		
		
		var response = new CadastrarTurmaResponseDto();
		response.setId(turma.getId_turma());
		response.setNumeroTurma(turma.getNumeroTurma());
		response.setAnoLetivo(turma.getAnoLetivo());
		
		return response;
	}
	
}
