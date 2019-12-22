package com.allissonjardel.projeto.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cargo extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(max = 60, message = "{Size.cargo.nome}")
	@Column(nullable=false, unique=true, length=60)
	private String nome;

	@NotNull
	@ManyToOne
	private Departamento departamento;

	@OneToMany(mappedBy="cargo")
	private List<Funcionario> funcionarios;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	
	
}
