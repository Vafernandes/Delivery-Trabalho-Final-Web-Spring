package com.delivery.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Produto> produtos;
	
	private boolean concluido;
	
	private String endereco;
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Pedido() {
		produtos = new ArrayList<Produto>();
		this.concluido = false;
	}
	
	public void removerProduto(Long id) {
		for(Produto produto: produtos)
			if(produto.getIdProduto() == id) {
				produtos.remove(produto);
				break;
			}
	}
	
	public float calcularValor() {
		float valor = 0f;
		for(Produto produto: produtos) {
			valor += produto.getPreco();
		}
		return valor;
	}
	
	public void setConcluido() {
		this.concluido = true;
	}
	
	public void adicionarProduto(Produto produto) {
		this.produtos.add(produto);
	}
	
	public List<Produto> getProdutos(){
		return this.produtos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
