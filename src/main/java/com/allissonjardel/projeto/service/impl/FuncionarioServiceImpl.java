package com.allissonjardel.projeto.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allissonjardel.projeto.model.Funcionario;
import com.allissonjardel.projeto.repository.FuncionarioRepository;
import com.allissonjardel.projeto.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{

	@Autowired
	FuncionarioRepository repository;
	
	@Override
	public void insert(Funcionario entity) {
		repository.save(entity);
	}

	@Override
	public void update(Funcionario entity, Long id) {
		Funcionario entity2 = repository.findById(id).get();
		BeanUtils.copyProperties(entity, entity2, "id");
		repository.save(entity2);
	}

	@Override
	public Funcionario findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Funcionario> getAll() {
		return repository.findAll();
	}
	
	@Override
	public List<Funcionario> getByName(String nome) {
		return repository.getFuncionariosByName(nome);
	}
	
	@Override
	public List<Funcionario> getByCargo(Long id) {
		return repository.getFuncionariosByCargo(id);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);	
	}

	@Override
	public List<Funcionario> getForDates(LocalDate entrada, LocalDate saida) {
		if(entrada != null && saida != null) {
			return repository.getFuncionariosByDataEntradaSaida(entrada, saida);
		}else if(entrada != null) {
			return repository.getFuncionariosByDataEntrada(entrada);
		}else if(saida != null) {
			return repository.getFuncionariosByDataSaida(saida);
		}else {
			return new ArrayList<>();
		}
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
