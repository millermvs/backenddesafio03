package br.com.desafio.domain.dtos.request.aluno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarAlunoRequestDto {

	@Pattern(regexp = "^[A-Za-zÀ-Üà-ü\\s]{3,100}$", message = "Campo inválido.")
	private String nome;

	@Email
	private String email;

	@Pattern(regexp = "^[0-9]{11}$", message = "CPF inválido")
	private String cpf;

	@Pattern(regexp = "^[0-9]{2,4}$", message = "Campo inválido. Min.: 2, máx.: 4 caracteres.")
	private String numeroTurma;

	@Pattern(regexp = "^[0-9]{2,4}$", message = "Campo inválido. Min.: 2, máx.: 4 caracteres.")
	private String anoLetivo;

}
