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
@Table(name = "turmas")
public class Turma {
	
	@Id
	@Column
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Long id_turma;
	
	@Column
	private String numeroTurma;
	
	@Column
	private String anoLetivo;
	
	@Column
	private Long id_aluno1;
	
	@Column
	private Long id_aluno2;
	
	@Column
	private Long id_aluno3;
	
	@Column
	private Long id_aluno4;
	
	@Column
	private Long id_aluno5;

}
