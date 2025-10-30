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

}
