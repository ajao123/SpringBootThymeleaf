package com.allissonjardel.projeto.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allissonjardel.projeto.model.Cargo;
import com.allissonjardel.projeto.repository.CargoRepository;
import com.allissonjardel.projeto.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService{

	@Autowired
	CargoRepository repository;
	
	@Override
	public void insert(Cargo entity) {
		repository.save(entity);
	}

	@Override
	public void update(Cargo entity, Long id) {
		Cargo entity2 = repository.findById(id).get();
		BeanUtils.copyProperties(entity, entity2, "id");
		repository.save(entity2);
	}

	@Override
	public Cargo findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Cargo> getAll() {
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
