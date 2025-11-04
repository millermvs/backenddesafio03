package br.com.desafio.domain.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.domain.dtos.CadastraAlunoTurmaRequestDto;
import br.com.desafio.domain.dtos.CadastraAlunoTurmaResponseDto;
import br.com.desafio.domain.dtos.CadastrarTurmaRequestDto;
import br.com.desafio.domain.dtos.CadastrarTurmaResponseDto;
import br.com.desafio.domain.dtos.ConsultarTurmaResponseDto;
import br.com.desafio.domain.dtos.DeletarTurmaResponseDto;
import br.com.desafio.domain.dtos.EditarTurmaRequestDto;
import br.com.desafio.domain.dtos.EditarTurmaResponseDto;
import br.com.desafio.domain.entities.Turma;
import br.com.desafio.domain.exceptions.TurmaCheiaException;
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

	public ConsultarTurmaResponseDto consultarTurma(String nome) {
		var turmaFound = turmaRepository.findByNome(nome);

		if (turmaFound == null)
			throw new TurmaNaoEncontradaException();

		var turma = turmaFound;

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

	public Boolean verificarAlunoNestaTurma(CadastraAlunoTurmaRequestDto request) {

		var turmaFound = turmaRepository.findByNome(request.getNumeroTurma());

		if (turmaFound == null)
			throw new TurmaNaoEncontradaException();

		var alunoNovo = request.getIdAluno();

		var aluno01 = turmaFound.getId_aluno1();
		var aluno02 = turmaFound.getId_aluno2();
		var aluno03 = turmaFound.getId_aluno3();
		var aluno04 = turmaFound.getId_aluno4();
		var aluno05 = turmaFound.getId_aluno5();

		if (alunoNovo.equals(aluno01) || alunoNovo.equals(aluno02) || alunoNovo.equals(aluno03)
				|| alunoNovo.equals(aluno04) || alunoNovo.equals(aluno05))
			return true;

		return false;
	}

	public CadastraAlunoTurmaResponseDto turmaCadastrarAluno(CadastraAlunoTurmaRequestDto request) {

		var turmaFound = turmaRepository.findByNome(request.getNumeroTurma());

		var aluno01 = turmaFound.getId_aluno1();
		var aluno02 = turmaFound.getId_aluno2();
		var aluno03 = turmaFound.getId_aluno3();
		var aluno04 = turmaFound.getId_aluno4();
		var aluno05 = turmaFound.getId_aluno5();

		if (aluno01 != null && aluno02 != null && aluno03 != null && aluno04 != null && aluno05 != null)
			throw new TurmaCheiaException();

		var response = new CadastraAlunoTurmaResponseDto();

		List<Long> listAlunos = new ArrayList<Long>();
		listAlunos.add(aluno01);
		listAlunos.add(aluno02);
		listAlunos.add(aluno03);
		listAlunos.add(aluno04);
		listAlunos.add(aluno05);

			for (var i = 0; i < listAlunos.size(); i++) {
				if (listAlunos.get(i)==null) {
					listAlunos.set(i, request.getIdAluno());					
					turmaFound.setId_aluno1(listAlunos.get(0));
					turmaFound.setId_aluno2(listAlunos.get(1));
					turmaFound.setId_aluno3(listAlunos.get(2));
					turmaFound.setId_aluno4(listAlunos.get(3));
					turmaFound.setId_aluno5(listAlunos.get(4));
					turmaRepository.save(turmaFound);
					break;
				}
				
		}

			response.setIdAluno(request.getIdAluno());
			response.setIdTurma(turmaFound.getId_turma());
		
		
		return response;

	}

	public Boolean turmaCheia(String numeroTurma) {

		var turmaFound = turmaRepository.findByNome(numeroTurma);

		var aluno01 = turmaFound.getId_aluno1();
		var aluno02 = turmaFound.getId_aluno2();
		var aluno03 = turmaFound.getId_aluno3();
		var aluno04 = turmaFound.getId_aluno4();
		var aluno05 = turmaFound.getId_aluno5();

		if (aluno01 != null && aluno02 != null && aluno03 != null && aluno04 != null && aluno05 != null)
			return true;
		
		return false;
	}
}
