package com.allissonjardel.projeto.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.allissonjardel.projeto.model.Cargo;
import com.allissonjardel.projeto.model.Departamento;
import com.allissonjardel.projeto.service.CargoService;
import com.allissonjardel.projeto.service.DepartamentoService;
import com.allissonjardel.projeto.validator.CargoValidator;

@Controller
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	CargoService service;
	
	@Autowired
	DepartamentoService departamentoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new CargoValidator(service));
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "cargo/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Cargo entity, BindingResult result, RedirectAttributes attr) {
			
		if(result.hasErrors()) 
			return "cargo/cadastro";
		
		
		service.insert(entity);
		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos", service.getAll());
		return "cargo/lista";
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos(){
		return departamentoService.getAll();
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cargo", service.findById(id));
		return "cargo/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Cargo entity, BindingResult result, RedirectAttributes attr) {

		if(result.hasErrors()) 
			return "cargo/cadastro";
		
		service.update(entity, entity.getId());
		attr.addFlashAttribute("success", "Cargo atualizado com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		
		if(service.findById(id).getFuncionarios().isEmpty()) {
			service.delete(id);
			model.addAttribute("success", "Cargo removido com sucesso.");
		}else {
			model.addAttribute("fail", "Cargo não removido. Possui funcionario(s) vinculado(s).");
		}	
		return listar(model);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
