package br.com.desafio.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.domain.dtos.CadastrarTurmaRequestDto;
import br.com.desafio.domain.dtos.CadastrarTurmaResponseDto;
import br.com.desafio.domain.dtos.ConsultarTurmaResponseDto;
import br.com.desafio.domain.dtos.DeletarTurmaResponseDto;
import br.com.desafio.domain.dtos.EditarTurmaRequestDto;
import br.com.desafio.domain.dtos.EditarTurmaResponseDto;
import br.com.desafio.domain.entities.Turma;
import br.com.desafio.domain.exceptions.TurmaNaoEncontradaException;
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

	public EditarTurmaResponseDto editarTurma(EditarTurmaRequestDto request) {

		var turmaFound = turmaRepository.findById(request.getId_turma());

		if (turmaFound.isEmpty())
			throw new TurmaNaoEncontradaException();
		
		var turma = turmaFound.get();
		turma.setNumeroTurma(request.getNumeroTurma());
		turma.setAnoLetivo(request.getAnoLetivo());
		turmaRepository.save(turma);
		
		var response = new EditarTurmaResponseDto();
		response.setId_turma(turma.getId_turma());
		response.setNumeroTurma(turma.getNumeroTurma());
		response.setAnoLetivo(turma.getAnoLetivo());

		return response;

	}
	
	public ConsultarTurmaResponseDto consultarTurma(Long id) {
		var turmaFound = turmaRepository.findById(id);

		if (turmaFound.isEmpty())
			throw new TurmaNaoEncontradaException();
		
		var turma = turmaFound.get();
		
		var response = new ConsultarTurmaResponseDto();
		response.setId_turma(turma.getId_turma());
		response.setNumeroTurma(turma.getNumeroTurma());
		response.setAnoLetivo(turma.getAnoLetivo());		
		
		return response;
	}
	
	public DeletarTurmaResponseDto deletarTurma(Long id) {
		
		var turmaFound = turmaRepository.findById(id);

		if (turmaFound.isEmpty())
			throw new TurmaNaoEncontradaException();
		
		turmaRepository.deleteById(id);
		
		var response = new DeletarTurmaResponseDto();
		response.setNumeroTurma(turmaFound.get().getNumeroTurma());
		response.setAnoLetivo(turmaFound.get().getAnoLetivo());
		
		return response;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
