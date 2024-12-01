package br.com.catalogoprodutossustentaveis.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.catalogoprodutossustentaveis.service.ProdutoService;
import br.com.catalogoprodutossustentaveis.service.CategoriaService;
import br.com.catalogoprodutossustentaveis.service.FornecedorService;

@Controller
@RequestMapping("/web/administracao")
public class AuthController {

	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/login")
	public String login() {
		return "loginform";
	}

	@GetMapping("/paineladministrativo")
	public String painelAdministrativo(Model model) {
		long totalFornecedores = fornecedorService.contarFornecedores();
		long totalCategorias = categoriaService.contarCategorias();
		long totalProdutos = produtoService.contarProdutos();

		model.addAttribute("totalFornecedores", totalFornecedores);
		model.addAttribute("totalCategorias", totalCategorias);
		model.addAttribute("totalProdutos", totalProdutos);

		return "paineladministrativo";
	}
}
