 package com.allissonjardel.projeto.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Departamento extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(min = 3, max = 60)
	@Column(name="nome", nullable=false, unique=true, length=60)
	private String nome;

	@OneToMany(mappedBy="departamento")
	List<Cargo> cargos;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
}
