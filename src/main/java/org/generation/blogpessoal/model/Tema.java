package org.generation.blogpessoal.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_tema")
public class Tema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String descricao;
	
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"postagemCriada"})
	private List<Postagem> postagemCriada = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagemCriada() {
		return postagemCriada;
	}

	public void setPostagemCriada(List<Postagem> postagemCriada) {
		this.postagemCriada = postagemCriada;
	}

	public void setId(Long id) {
		this.id = id;
	}



}
