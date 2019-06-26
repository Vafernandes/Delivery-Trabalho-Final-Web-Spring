package com.delivery.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.delivery.model.Pessoa;
import com.delivery.service.PessoaService;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping("/formulario")
	public ModelAndView retornaForm() {
		
		ModelAndView mv = new ModelAndView("/cliente/Formulario");
		mv.addObject("pessoa", new Pessoa());
		return mv;
	}
	
	@RequestMapping("/salvar")
	public ModelAndView salvar(@Validated Pessoa pessoa, BindingResult result) {
		
		ModelAndView mv = new ModelAndView("/cliente/Formulario"); 
		
		if(result.hasErrors()) {
			return mv; 
		}
		
		mv.addObject("mensagem", "Pessoa cadastrada com sucesso!"); 
		pessoaService.cadastrar(pessoa);
		
		return mv;
	}
	
	@RequestMapping("/logar")
	public ModelAndView formLogin() {
		ModelAndView mv = new ModelAndView("/cliente/entrar");
		return mv;
	}
	
}
