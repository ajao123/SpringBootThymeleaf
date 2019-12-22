package com.allissonjardel.projeto.service;

import java.util.List;

import com.allissonjardel.projeto.model.Endereco;

public interface EnderecoService {

	void insert(Endereco entity);
	void update(Endereco entity, Long id);
	Endereco findById(Long id);
	List<Endereco> getAll();
	void delete(Long id);
	
}
