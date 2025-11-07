package br.com.desafio.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.domain.dtos.request.aluno.AddAlunoToTurmaRequestDto;
import br.com.desafio.domain.dtos.request.aluno.CadastrarAlunoRequestDto;
import br.com.desafio.domain.dtos.request.aluno.EditarAlunoRequestDto;
import br.com.desafio.domain.dtos.response.aluno.AddAlunoToTurmaResponseDto;
import br.com.desafio.domain.dtos.response.aluno.CadastrarAlunoResponseDto;
import br.com.desafio.domain.dtos.response.aluno.ConsultarAlunoResponseDto;
import br.com.desafio.domain.dtos.response.aluno.DeletarAlunoResponseDto;
import br.com.desafio.domain.dtos.response.aluno.EditarAlunoResponseDto;
import br.com.desafio.domain.entities.Aluno;
import br.com.desafio.domain.exceptions.AlunoJaCadastradoNestaTurmaException;
import br.com.desafio.domain.exceptions.AlunoNaoEncontradoException;
import br.com.desafio.domain.exceptions.AlunoNaoPodeSerExcluidoException;
import br.com.desafio.domain.exceptions.TurmaCheiaException;
import br.com.desafio.domain.exceptions.TurmaNaoEncontradaException;
import br.com.desafio.infrastructure.repositories.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private TurmaService turmaService;

	public CadastrarAlunoResponseDto cadastrarAluno(CadastrarAlunoRequestDto request) {

		var turmaFound = turmaService.consultarTurma(request.getNumeroTurma());
		if (turmaFound == null)
			throw new TurmaNaoEncontradaException();

		var verificarTurmaCheia = turmaService.turmaCheia(request.getNumeroTurma());
		if (verificarTurmaCheia == true)
			throw new TurmaCheiaException();

		var aluno = new Aluno();
		aluno.setNome(request.getNome());
		aluno.setCpf(request.getCpf());
		aluno.setEmail(request.getEmail());
		alunoRepository.save(aluno);
		
		//to do to do to do

		var cadastraAlunoTurma = new AddAlunoToTurmaRequestDto();
		cadastraAlunoTurma.setIdAluno(aluno.getIdAluno());
		cadastraAlunoTurma.setNumeroTurma(request.getNumeroTurma());

		// var alunoCadastrado = cadastrarAlunoNaTurma(cadastraAlunoTurma);

		var editarAluno = new EditarAlunoRequestDto();
		editarAluno.setId(aluno.getIdAluno());
		editarAluno.setNome(aluno.getNome());
		editarAluno.setCpf(aluno.getCpf());
		editarAluno.setEmail(aluno.getEmail());

		editarAluno(editarAluno);

		var response = new CadastrarAlunoResponseDto();
		response.setId(aluno.getIdAluno());
		response.setNome(aluno.getNome());
		response.setEmail(aluno.getEmail());
		response.setTurma(request.getNumeroTurma());

		return response;
	}

	public EditarAlunoResponseDto editarAluno(EditarAlunoRequestDto request) {

		var alunoFound = alunoRepository.findById(request.getId());
		if (alunoFound.isEmpty())
			throw new AlunoNaoEncontradoException();

		var aluno = alunoFound.get();
		aluno.setNome(request.getNome());
		aluno.setCpf(request.getCpf());
		aluno.setEmail(request.getEmail());
		alunoRepository.save(aluno);

		var response = new EditarAlunoResponseDto();
		response.setCpf(aluno.getCpf());
		response.setEmail(aluno.getEmail());
		response.setNome(aluno.getNome());

		return response;

	}

	public ConsultarAlunoResponseDto consultarAluno(String cpf) {

		var alunoFound = alunoRepository.findByCpf(cpf);
		if (alunoFound == null)
			throw new AlunoNaoEncontradoException();

		var response = new ConsultarAlunoResponseDto();
		response.setId(alunoFound.getIdAluno());
		response.setNome(alunoFound.getNome());
		response.setCpf(alunoFound.getCpf());
		response.setEmail(alunoFound.getEmail());

		return response;
	}

	public DeletarAlunoResponseDto deletarAluno(Long id) {

		var alunosFound = alunoRepository.findByIdAluno(id);
		if (alunosFound.isEmpty())
			throw new AlunoNaoEncontradoException();		
		
		if (alunosFound.get().getTurmas().size()>0)
			throw new AlunoNaoPodeSerExcluidoException();
		
		alunoRepository.deleteById(id);

		var alunoDeletado = alunosFound.get();

		var response = new DeletarAlunoResponseDto();
		response.setNome(alunoDeletado.getNome());
		response.setCpf(alunoDeletado.getCpf());
		response.setEmail(alunoDeletado.getEmail());
		response.setReposta("Aluno foi deletado.");

		return response;

	}
	
	public AddAlunoToTurmaResponseDto cadastrarAlunoNaTurma(AddAlunoToTurmaRequestDto request) {

		var verificarAlunoNestaTurma = turmaService.verificarAlunoNestaTurma(request);

		if (verificarAlunoNestaTurma == true)
			throw new AlunoJaCadastradoNestaTurmaException();

		var verificarTurmaCheia = turmaService.turmaCheia(request.getNumeroTurma());
		if (verificarTurmaCheia == true)
			throw new TurmaCheiaException();

		var turmaCadastrarAluno = turmaService.turmaCadastrarAluno(request);

		var response = new AddAlunoToTurmaResponseDto();
		response.setIdAluno(turmaCadastrarAluno.getIdAluno());
		response.setIdTurma(turmaCadastrarAluno.getIdTurma());
		response.setNomeAluno(turmaCadastrarAluno.getNomeAluno());
		response.setNumeroTurma(turmaCadastrarAluno.getNumeroTurma());
		response.setResposta(turmaCadastrarAluno.getResposta());

		return response;

	}

}
