package br.com.desafio.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarAlunoRequestDto {

	@NotEmpty(message = "Campo não pode ser nulo.")
	private String nome;
	
	
	@NotEmpty(message = "Campo não pode ser nulo.")
	private String email;
	
	
	@NotEmpty(message = "Campo não pode ser nulo.")
	private String cpf;
	
	
	@NotEmpty(message = "Campo não pode ser nulo.")
	private String numeroTurma;
	
	
	@NotEmpty(message = "Campo não pode ser nulo.")
	private String anoLetivo;

}
