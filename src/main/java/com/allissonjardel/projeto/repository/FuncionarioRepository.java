package com.allissonjardel.projeto.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allissonjardel.projeto.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	@Query(value = "SELECT * FROM FUNCIONARIO WHERE NOME LIKE CONCAT('%', ?1, '%') ", nativeQuery = true)
	List<Funcionario> getFuncionariosByName(String nome);
	
	@Query(value = "SELECT * FROM FUNCIONARIO WHERE CARGO_ID = ?1", nativeQuery = true)
	List<Funcionario> getFuncionariosByCargo(Long id);
	
	@Query(value = "SELECT * FROM FUNCIONARIO WHERE DATA_ENTRADA = ?1 ORDER BY DATA_ENTRADA ASC", nativeQuery = true)
	List<Funcionario> getFuncionariosByDataEntrada(LocalDate entrada);
	
	@Query(value = "SELECT * FROM FUNCIONARIO WHERE DATA_SAIDA = ?1 ORDER BY DATA_ENTRADA ASC", nativeQuery = true)
	List<Funcionario> getFuncionariosByDataSaida(LocalDate saida);
	
	@Query(value = "SELECT * FROM FUNCIONARIO WHERE DATA_ENTRADA >= ?1 and DATA_SAIDA <= ?2 ORDER BY DATA_ENTRADA ASC", nativeQuery = true)
	List<Funcionario> getFuncionariosByDataEntradaSaida(LocalDate entrada, LocalDate saida);
	
	@Query(value = "SELECT COUNT(*) FROM FUNCIONARIO WHERE NOME = ?1 AND ID != ?2", nativeQuery = true)
	Integer existByName(String nome, Long id);
	
	@Query(value = "SELECT COUNT(*) FROM FUNCIONARIO WHERE NOME = ?1", nativeQuery = true)
	Integer existByName(String nome);
	
}
