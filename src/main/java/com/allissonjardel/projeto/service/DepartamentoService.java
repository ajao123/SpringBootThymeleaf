package com.allissonjardel.projeto.service;

import java.util.List;

import com.allissonjardel.projeto.model.Departamento;

public interface DepartamentoService {

	void insert(Departamento entity);
	void update(Departamento entity, Long id);
	Departamento findById(Long id);
	List<Departamento> getAll();
	void delete(Long id);
	Integer existByName(String nome, Long id);
	Integer existByName(String nome);
	
}
