package br.com.desafio.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.domain.dtos.CadastrarAlunoRequestDto;
import br.com.desafio.domain.dtos.CadastrarAlunoResponseDto;
import br.com.desafio.domain.dtos.CadastrarTurmaRequestDto;
import br.com.desafio.domain.dtos.ConsultarAlunoResponseDto;
import br.com.desafio.domain.dtos.DeletarAlunoResponseDto;
import br.com.desafio.domain.dtos.EditarAlunoRequestDto;
import br.com.desafio.domain.dtos.EditarAlunoResponseDto;
import br.com.desafio.domain.entities.Aluno;
import br.com.desafio.domain.exceptions.AlunoNaoEncontradoException;
import br.com.desafio.infrastructure.repositories.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private TurmaService turmaService;

	public CadastrarAlunoResponseDto cadastrarAluno(CadastrarAlunoRequestDto request) {

		var cadastrarTurmaRequestDto = new CadastrarTurmaRequestDto();
		cadastrarTurmaRequestDto.setAnoLetivo(request.getAnoLetivo());
		cadastrarTurmaRequestDto.setNumeroTurma(request.getNumeroTurma());

		var responseTurmaCadastrada = turmaService.cadastrarTurma(cadastrarTurmaRequestDto);

		var aluno = new Aluno();
		aluno.setNome(request.getNome());
		aluno.setCpf(request.getCpf());
		aluno.setEmail(request.getEmail());
		aluno.setIdsTurmasDoAluno(responseTurmaCadastrada.getId());

		alunoRepository.save(aluno);

		var response = new CadastrarAlunoResponseDto();
		response.setId(aluno.getId_aluno());
		response.setNome(aluno.getNome());
		response.setEmail(aluno.getEmail());
		response.setTurma(responseTurmaCadastrada.getNumeroTurma());

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
		response.setId(alunoFound.getId_aluno());
		response.setNome(alunoFound.getNome());
		response.setCpf(alunoFound.getCpf());
		response.setEmail(alunoFound.getEmail());

		return response;
	}

	public DeletarAlunoResponseDto deletarAluno(Long id) {

		var alunoFound = alunoRepository.findById(id);
		if (alunoFound.isEmpty())
			throw new AlunoNaoEncontradoException();

		alunoRepository.deleteById(id);

		var alunoDeletado = alunoFound.get();

		var response = new DeletarAlunoResponseDto();
		response.setNome(alunoDeletado.getNome());
		response.setCpf(alunoDeletado.getCpf());
		response.setEmail(alunoDeletado.getEmail());

		return response;

	}

}
