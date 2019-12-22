package com.allissonjardel.projeto.web.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.allissonjardel.projeto.model.Cargo;
import com.allissonjardel.projeto.model.Funcionario;
import com.allissonjardel.projeto.model.enums.UF;
import com.allissonjardel.projeto.service.CargoService;
import com.allissonjardel.projeto.service.FuncionarioService;
import com.allissonjardel.projeto.validator.FuncionarioValidator;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	FuncionarioService service;
	
	@Autowired
	CargoService cargoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new FuncionarioValidator(service));
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "funcionario/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Funcionario entity, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) 
			return "funcionario/cadastro";
		
		service.insert(entity);
		attr.addFlashAttribute("success", "Funcionario adicionado com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", service.getAll());
		return "funcionario/lista";
	}
	
	@ModelAttribute("cargos")
	public List<Cargo> listaDeCargos(){
		return cargoService.getAll();
	}
	
	@ModelAttribute("ufs")
	public UF[] getUFs() {
		return UF.values();
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario", service.findById(id));
		return "funcionario/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Funcionario entity, BindingResult result, RedirectAttributes attr) {

		if(result.hasErrors()) 
			return "funcionario/cadastro";
		
		
		service.update(entity, entity.getId());
		attr.addFlashAttribute("sucess", "Funcionario atualizado com sucesso");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {

		service.delete(id);
		model.addAttribute("success", "Funcionario removido com sucesso.");
		return listar(model);
	}
	
	@GetMapping("/buscar/nome")
	public String getFuncionariosByNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("funcionarios", service.getByName(nome));
		return "funcionario/lista";
	}
	
	@GetMapping("/buscar/cargo")
	public String getFuncionariosByCargo(@RequestParam("id") Long id, ModelMap model) {
		model.addAttribute("funcionarios", service.getByCargo(id));
		return "funcionario/lista";
	}

	@GetMapping("/buscar/data")
	public String getPorDatas(@RequestParam("entrada") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada, 
							  @RequestParam("saida") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida, 
			ModelMap model) {
		model.addAttribute("funcionarios", service.getForDates(entrada, saida));
		return "funcionario/lista";
	}
	
}

































