package br.com.desafio.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.desafio.domain.entities.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

	@Query("""
			SELECT t FROM Turma t
			WHERE t.numeroTurma = :pnumeroTurma
			""")
	Turma findByNome(@Param("pnumeroTurma") String numeroTurma);

}
