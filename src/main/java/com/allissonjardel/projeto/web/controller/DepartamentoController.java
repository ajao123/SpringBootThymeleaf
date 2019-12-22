package com.allissonjardel.projeto.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.allissonjardel.projeto.model.Departamento;
import com.allissonjardel.projeto.service.DepartamentoService;
import com.allissonjardel.projeto.validator.DepartamentoValidator;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	DepartamentoService service;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new DepartamentoValidator(service));
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "departamento/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Departamento entity, BindingResult result, RedirectAttributes attr) {
			
		if(result.hasErrors()) 
			return "departamento/cadastro";
		
		service.insert(entity);
		attr.addFlashAttribute("success", "Departamento inserido com sucesso.");	

		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", service.getAll());
		return "departamento/lista";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("departamento", service.findById(id));
		return "departamento/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Departamento entity, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) 
			return "departamento/cadastro";
		
		service.update(entity, entity.getId());
		attr.addFlashAttribute("success", "Departamento atualizado com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		
		if(service.findById(id).getCargos().isEmpty()) {
			service.delete(id);
			model.addAttribute("success", "Departamento removido com sucesso.");
		}else {
			model.addAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s).");
		}
		return listar(model);
	}
	
}
