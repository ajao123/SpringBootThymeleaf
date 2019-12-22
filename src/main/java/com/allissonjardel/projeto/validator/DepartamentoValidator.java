package com.allissonjardel.projeto.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.allissonjardel.projeto.model.Departamento;
import com.allissonjardel.projeto.service.DepartamentoService;

public class DepartamentoValidator implements Validator{

	DepartamentoService service;
	
	public DepartamentoValidator(DepartamentoService service) {
		this.service = service;	
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Departamento.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Departamento entity = (Departamento) object;
		
		if(entity.getId() == null) {
			if(service.existByName(entity.getNome()) != 0) {      
				errors.rejectValue("nome", "Existe.nome");
			}	
		}else {
			if(service.existByName(entity.getNome(), entity.getId()) != 0) {    
				errors.rejectValue("nome", "Existe.nome");
			}
		}
	}

}
