package com.delivery.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.delivery.model.Pessoa;
import com.delivery.model.Role;
import com.delivery.repository.PessoaRepository; 

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	//@Autowired
	//private RoleService roleService;
	
	public void cadastrar(Pessoa pessoa) {
		
		pessoa.setSenha(new BCryptPasswordEncoder().encode(pessoa.getSenha()));
		
		Role role = new Role();
		role.setPapel("ROLE_USER");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		pessoa.setRoles(roles);
		
		pessoaRepository.save(pessoa);
	}

	public List<Pessoa> listarPessoas() {
		return pessoaRepository.findAll();
	}

	public void excluir(Long codigo) {
		pessoaRepository.deleteById(codigo);
	}

	public Pessoa buscarPorId(Long codigo) {
		return pessoaRepository.getOne(codigo);
	}
	
	public Pessoa obterPessoa(String email) {
		return pessoaRepository.findByEmail(email);
	}
	
}
