package com.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.delivery.model.Produto;
import com.delivery.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	 
	
	@RequestMapping("/formulario")
	public ModelAndView cadastrar() {
		
		ModelAndView mv = new ModelAndView("/prato/FormularioProduto");
		mv.addObject("produto", new Produto());
		
		return mv;
	}
	
	@RequestMapping("/salvar") 
	public ModelAndView salvar(@Validated Produto produto, BindingResult result, 
			@RequestParam(value= "imagem") MultipartFile imagem) {
		
		ModelAndView mv = new ModelAndView("/prato/FormularioProduto");
		
		if(result.hasErrors()) {
			return mv;
		} 
		
		mv.addObject("mensagem", "Produto cadastrado com sucesso!");
		produtoService.cadastrar(produto, imagem);
		
		return mv;
		
	}
	
	@RequestMapping("/listar")
	public ModelAndView listar() {
		
		List<Produto> produtos = produtoService.listarPedidos();
		
		ModelAndView mv = new ModelAndView("/prato/ListarProdutos");
		mv.addObject("listaDeProdutos", produtos); 
		
		return mv;
		
	}
	
	@RequestMapping("/cardapio")
	public ModelAndView cardapio() {
		List<Produto> produtos = produtoService.listarPedidos();
		
		ModelAndView mv = new ModelAndView("/prato/Cardapio");
		mv.addObject("listaDeProdutos", produtos); 
		
		return mv;
	}
	
	@RequestMapping("/excluir/{idProduto}")
	public ModelAndView excluir(@PathVariable Long idProduto) {
		produtoService.excluir(idProduto);
		
		ModelAndView mv = new ModelAndView("redirect:/produto/listar");
		
		return mv;
	}
	
	@RequestMapping("/atualizar/{idProduto}")
	public ModelAndView atualizar(@PathVariable Long idProduto) {
		
		Produto produto = produtoService.atualizar(idProduto);
		
		ModelAndView mv = new ModelAndView("/prato/FormularioProduto");
		mv.addObject("produto", produto);
		
		return mv;
	}
	
	
}
