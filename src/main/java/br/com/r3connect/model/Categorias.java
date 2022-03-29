package br.com.r3connect.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_categorias")
public class Categorias {

	//ATRIBUTOS
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O atributo material é obrigatório!")
	@Size(min = 5, max = 100, message = "O atributo Material deve possuir no mínimo 5 e no máximo 100 caracteres.")
	private String material;
	
	@NotNull(message = "O atributo descricao é obrigatório!")
	@Size(min = 5, max = 1000, message = "O atributo Material deve possuir no mínimo 5 e no máximo 1000 caracteres.")
	private String descricao;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
