package br.com.desafio.domain.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.domain.dtos.request.aluno.AddAlunoToTurmaRequestDto;
import br.com.desafio.domain.dtos.request.aluno.AlunoResumoDto;
import br.com.desafio.domain.dtos.request.turma.CadastrarTurmaRequestDto;
import br.com.desafio.domain.dtos.request.turma.EditarTurmaRequestDto;
import br.com.desafio.domain.dtos.response.aluno.AddAlunoToTurmaResponseDto;
import br.com.desafio.domain.dtos.response.turma.CadastrarTurmaResponseDto;
import br.com.desafio.domain.dtos.response.turma.ConsultarTurmaResponseDto;
import br.com.desafio.domain.dtos.response.turma.DeletarTurmaResponseDto;
import br.com.desafio.domain.dtos.response.turma.EditarTurmaResponseDto;
import br.com.desafio.domain.entities.Turma;
import br.com.desafio.domain.exceptions.AlunoJaCadastradoNestaTurmaException;
import br.com.desafio.domain.exceptions.AlunoNaoEncontradoException;
import br.com.desafio.domain.exceptions.TurmaNaoEncontradaException;
import br.com.desafio.domain.exceptions.TurmaNaoPodeSerExcluidaException;
import br.com.desafio.infrastructure.repositories.AlunoRepository;
import br.com.desafio.infrastructure.repositories.TurmaRepository;
import jakarta.transaction.Transactional;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	public CadastrarTurmaResponseDto cadastrarTurma(CadastrarTurmaRequestDto request) {

		var turma = new Turma();
		turma.setNumeroTurma(request.getNumeroTurma());
		turma.setAnoLetivo(request.getAnoLetivo());
		turmaRepository.save(turma);

		var response = new CadastrarTurmaResponseDto();
		response.setId(turma.getIdTurma());
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
		response.setId_turma(turma.getIdTurma());
		response.setNumeroTurma(turma.getNumeroTurma());
		response.setAnoLetivo(turma.getAnoLetivo());

		return response;
	}

	public ConsultarTurmaResponseDto consultarTurma(String nome) {

		var turmaFound = turmaRepository.findByNumeroTurma(nome);

		if (turmaFound.isEmpty())
			throw new TurmaNaoEncontradaException();

		List<AlunoResumoDto> listaDeAlunos = new ArrayList<AlunoResumoDto>();

		for (var aluno : turmaFound.get().getAlunos()) {
			var dtoItem = new AlunoResumoDto();
			dtoItem.setId(aluno.getIdAluno());
			dtoItem.setNome(aluno.getNome());
			listaDeAlunos.add(dtoItem);
		}

		var turma = turmaFound.get();
		var response = new ConsultarTurmaResponseDto();
		response.setId_turma(turma.getIdTurma());
		response.setNumeroTurma(turma.getNumeroTurma());
		response.setAnoLetivo(turma.getAnoLetivo());
		response.setAlunos(listaDeAlunos);
		return response;
	}

	public DeletarTurmaResponseDto deletarTurma(String numeroTurma) {

		var turmaFound = turmaRepository.findByNumeroTurma(numeroTurma);

		if (turmaFound.isEmpty())
			throw new TurmaNaoEncontradaException();
		
		if(turmaFound.get().getAlunos().size()>0)
			throw new TurmaNaoPodeSerExcluidaException();
	

		turmaRepository.deleteById(turmaFound.get().getIdTurma());

		var response = new DeletarTurmaResponseDto();
		response.setNumeroTurma(turmaFound.get().getNumeroTurma());
		response.setAnoLetivo(turmaFound.get().getAnoLetivo());
		response.setResposta("Turma excluída.");

		return response;
	}

	public Boolean verificarAlunoNestaTurma(AddAlunoToTurmaRequestDto request) {

		var turmaFound = turmaRepository.findByNumeroTurma(request.getNumeroTurma());

		if (turmaFound.isEmpty())
			throw new TurmaNaoEncontradaException();

		var alunosTurma = turmaFound.get().getAlunos();

		var alunoNovo = alunoRepository.findById(request.getIdAluno());
		if (alunoNovo.isEmpty())
			throw new AlunoNaoEncontradoException();

		if (alunosTurma.contains(alunoNovo.get()))
			throw new AlunoJaCadastradoNestaTurmaException();

		return false;
	}

	@Transactional
	public AddAlunoToTurmaResponseDto turmaCadastrarAluno(AddAlunoToTurmaRequestDto request) {

		var turmaFound = turmaRepository.findByNumeroTurma(request.getNumeroTurma());
		var alunoFound = alunoRepository.findById(request.getIdAluno()).get();

		if (turmaFound.isEmpty())
			throw new TurmaNaoEncontradaException();

		var turma = turmaFound.get();
		turma.getAlunos().add(alunoFound);
		alunoFound.getTurmas().add(turma);
		turmaRepository.save(turma);

		var response = new AddAlunoToTurmaResponseDto();
		response.setIdAluno(request.getIdAluno());
		response.setIdTurma(turma.getIdTurma());
		response.setNomeAluno(alunoFound.getNome());
		response.setNumeroTurma(turma.getNumeroTurma());
		response.setResposta("Adicionado na turma.");

		return response;

	}

	public Boolean turmaCheia(String numeroTurma) {

		var turmaFound = turmaRepository.findByNumeroTurma(numeroTurma).get();

		if (turmaFound.getAlunos().size() > 4)
			return true;

		return false;

	}
}
