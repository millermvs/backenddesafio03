package br.com.desafio.domain.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "turmas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Turma {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Long idTurma;

	@Column
	private String numeroTurma;

	@Column
	private String anoLetivo;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "turmas_alunos", joinColumns = @JoinColumn(name = "turma_id", referencedColumnName = "idTurma"), 
	inverseJoinColumns = @JoinColumn(name = "aluno_id", referencedColumnName = "idAluno"), 
	uniqueConstraints = @UniqueConstraint(columnNames = {"turma_id", "aluno_id" })
	)
	private Set<Aluno> alunos;

}
