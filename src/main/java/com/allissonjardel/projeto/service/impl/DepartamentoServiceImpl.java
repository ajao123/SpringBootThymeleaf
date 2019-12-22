package com.allissonjardel.projeto.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allissonjardel.projeto.model.Departamento;
import com.allissonjardel.projeto.repository.DepartamentoRepository;
import com.allissonjardel.projeto.service.DepartamentoService;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{

	@Autowired
	DepartamentoRepository repository;
	
	@Override
	public void insert(Departamento entity) {
		repository.save(entity);
	}

	@Override
	public void update(Departamento entity, Long id) {
		Departamento entity2 = repository.findById(id).get();
		BeanUtils.copyProperties(entity, entity2, "id");
		repository.save(entity2);
	}

	@Override
	public Departamento findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Departamento> getAll() {
		return repository.findAll();
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);	
	}

	@Override
	public Integer existByName(String nome, Long id) {
		return repository.existByName(nome, id);
	}
	
	@Override
	public Integer existByName(String nome) {
		return repository.existByName(nome);
	}

}
