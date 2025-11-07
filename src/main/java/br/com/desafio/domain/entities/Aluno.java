package br.com.desafio.domain.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alunos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aluno {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Long idAluno;

	@Column
	private String nome;

	@Column
	private String cpf;

	@Column
	private String email;

	@ManyToMany(mappedBy = "alunos", fetch = FetchType.LAZY)
	private Set<Turma> turmas;
}
