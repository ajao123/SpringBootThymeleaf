package com.allissonjardel.projeto.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.allissonjardel.projeto.model.Cargo;
import com.allissonjardel.projeto.service.CargoService;

public class CargoValidator implements Validator{

	CargoService service;
	
	public CargoValidator(CargoService service) {
		this.service = service;	
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Cargo.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Cargo entity = (Cargo) object;
		
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
