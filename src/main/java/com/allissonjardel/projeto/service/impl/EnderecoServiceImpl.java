package com.allissonjardel.projeto.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allissonjardel.projeto.model.Endereco;
import com.allissonjardel.projeto.repository.EnderecoRepository;
import com.allissonjardel.projeto.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService{

	@Autowired
	EnderecoRepository repository;
	
	@Override
	public void insert(Endereco entity) {
		repository.save(entity);
	}

	@Override
	public void update(Endereco entity, Long id) {
		Endereco entity2 = repository.findById(id).get();
		BeanUtils.copyProperties(entity, entity2, "id");
		repository.save(entity2);
	}

	@Override
	public Endereco findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Endereco> getAll() {
		return repository.findAll();
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);	
	}

}
