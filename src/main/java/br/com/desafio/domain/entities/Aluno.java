package br.com.desafio.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alunos")
public class Aluno {

	@Id
	@Column
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Long id_aluno;
	
	@Column
	private String nome;
	
	@Column
	private String cpf;
	
	@Column
	private String email;
	
	@Column
	private Long idsTurmasDoAluno;

}
