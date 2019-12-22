package com.allissonjardel.projeto.service;

import java.time.LocalDate;
import java.util.List;

import com.allissonjardel.projeto.model.Funcionario;

public interface FuncionarioService {

	void insert(Funcionario entity);
	void update(Funcionario entity, Long id);
	Funcionario findById(Long id);
	List<Funcionario> getAll();
	List<Funcionario> getByName(String nome);
	List<Funcionario> getByCargo(Long id);
	List<Funcionario> getForDates(LocalDate entrada, LocalDate saida);
	void delete(Long id);
	Integer existByName(String nome, Long id);
	Integer existByName(String nome);
}
