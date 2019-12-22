package com.allissonjardel.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allissonjardel.projeto.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{

	@Query(value = "SELECT COUNT(*) FROM CARGO WHERE NOME = ?1 AND ID != ?2", nativeQuery = true)
	Integer existByName(String nome, Long id);
	
	@Query(value = "SELECT COUNT(*) FROM CARGO WHERE NOME = ?1", nativeQuery = true)
	Integer existByName(String nome);
	
}
