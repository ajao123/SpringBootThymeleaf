package com.allissonjardel.projeto.service;

import java.util.List;

import com.allissonjardel.projeto.model.Cargo;

public interface CargoService {

	void insert(Cargo entity);
	void update(Cargo entity, Long id);
	Cargo findById(Long id);
	List<Cargo> getAll();
	void delete(Long id);
	Integer existByName(String nome, Long id);
	Integer existByName(String nome);
}
