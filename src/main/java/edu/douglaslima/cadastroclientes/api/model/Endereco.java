package edu.douglaslima.cadastroclientes.api.model;

import jakarta.persistence.*;

@Embeddable
public class Endereco {
	
	private String cep;
	private String estado;
	private String cidade;
	private String bairro;
	private String logradouro;
	private String complemento;
	private int numero;
	
	public Endereco() {
		
	}
	
	public Endereco(String cep, String estado, String cidade) {
		this.cep = cep;
		this.estado = estado;
		this.cidade = cidade;
	}
	
	@Override
	public String toString() {
		return "Endereco [cep=" + cep + ", estado=" + estado + ", cidade=" + cidade + ", bairro=" + bairro
				+ ", logradouro=" + logradouro + ", complemento=" + complemento + ", numero=" + numero + "]";
	}

	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
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
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}
