package com.allissonjardel.projeto.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.allissonjardel.projeto.model.Funcionario;
import com.allissonjardel.projeto.service.FuncionarioService;

public class FuncionarioValidator implements Validator{

	FuncionarioService service;
	
	public FuncionarioValidator(FuncionarioService service) {
		this.service = service;	
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Funcionario.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Funcionario entity = (Funcionario) object;
		
		if(entity.getId() == null) {
			if(service.existByName(entity.getNome()) != 0) {      
				errors.rejectValue("nome", "Existe.nome");
			}	
		}else {
			if(service.existByName(entity.getNome(), entity.getId()) != 0) {    
				errors.rejectValue("nome", "Existe.nome");
			}
		}
		
		if(entity.getDataSaida() != null) {
			if(entity.getDataSaida().isBefore(entity.getDataEntrada())) {
				errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
			}
		}
		
	}

}
