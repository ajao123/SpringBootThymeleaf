package com.allissonjardel.projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.allissonjardel.projeto.model.enums.UF;

@Entity
public class Endereco extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(min=3, max=255)
	@Column(nullable=false)
	private String logradouro;
	
	@NotBlank
	@Size(min=3, max=255)
	@Column(nullable=false)
	private String cidade;
	
	@NotBlank
	@Size(min=3, max=255)
	@Column(nullable=false)
	private String bairro;
	
	@NotNull
	@Column(nullable=false, length=2)
	private UF uf;
	
	@NotBlank
	@Size(min=9, max=9, message = "{Size.endereco.cep}")
	@Column(nullable=false, length=9)
	private String cep;
	
	@NotNull(message = "{NotNull.endereco.numero}")
	@Digits(integer = 5, fraction = 0)
	@Column(nullable=false, length=5)
	private Integer numero;
	
	@Size(max=255)
	private String complemento;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
}
