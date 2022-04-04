package br.com.r3connect.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@NotNull(message = "O nome é obrigatório!")
	@Size(min = 5, max = 500)
	private String nome;
	
	@NotNull(message = "O e-mail é obrigatório!")
	@Email
	private Email usuario;
	
	@NotNull(message = "A senha é obrigatória!")
	@Size(min = 8)
	private String senha;
	
	private String foto;
	
	@NotNull(message = "O CEP é obrigatório!")
	@Size(min = 9, max = 9)
	private String cep;
	
	private BigDecimal contribuicao;

	
	
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Email getUsuario() {
		return usuario;
	}

	public void setUsuario(Email usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public BigDecimal getContribuicao() {
		return contribuicao;
	}

	public void setContribuicao(BigDecimal contribuicao) {
		this.contribuicao = contribuicao;
	}
	
	
	
	

}
