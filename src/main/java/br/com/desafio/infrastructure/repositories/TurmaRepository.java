package br.com.desafio.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.domain.entities.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
	
	@EntityGraph(attributePaths = "alunos")
	Optional<Turma> findByNumeroTurma(String numeroTurma);

}
