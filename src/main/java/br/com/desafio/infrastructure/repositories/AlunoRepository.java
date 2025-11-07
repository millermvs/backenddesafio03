package br.com.desafio.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.desafio.domain.entities.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	@EntityGraph(attributePaths = "turmas")
	Optional<Aluno> findByIdAluno(Long id);

	@Query("""
			SELECT a FROM Aluno a
			WHERE a.cpf = :pcpf
			""")
	Aluno findByCpf(@Param("pcpf") String cpf);
}
