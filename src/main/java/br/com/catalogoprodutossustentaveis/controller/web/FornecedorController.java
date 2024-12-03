package br.com.catalogoprodutossustentaveis.controller.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.catalogoprodutossustentaveis.dto.FornecedorDTO;
import br.com.catalogoprodutossustentaveis.model.FornecedorModel;
import br.com.catalogoprodutossustentaveis.repository.ProdutoRepository;
import br.com.catalogoprodutossustentaveis.service.FornecedorService;

@Controller
@RequestMapping("/web")
@Validated
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/administracao/fornecedores/novofornecedor")
	public String exibirFormularioNovoFornecedor(Model model) {
		model.addAttribute("fornecedor", new FornecedorModel());
		model.addAttribute("novo", true);
		return "fornecedorform";
	}

	@GetMapping("/administracao/fornecedores/editar/{id}")
	public String exibirFormularioEditarFornecedor(@PathVariable Long id, Model model) {
		fornecedorService.buscarFornecedorPorId(id).ifPresent(fornecedor -> {
			FornecedorDTO fornecedorDTO = new FornecedorDTO();
			fornecedorDTO.setId(fornecedor.getId());
			fornecedorDTO.setNome(fornecedor.getNome());
			fornecedorDTO.setEmail(fornecedor.getEmail());
			fornecedorDTO.setTelefone(fornecedor.getTelefone());
			fornecedorDTO.setEndereco(fornecedor.getEndereco());
			model.addAttribute("fornecedor", fornecedorDTO);
		});
		model.addAttribute("novo", false);
		return "fornecedorform";
	}

	@PostMapping("/administracao/fornecedores/novofornecedor")
	public String salvarFornecedor(@ModelAttribute FornecedorDTO fornecedorDTO, @RequestParam String action,
			RedirectAttributes redirectAttributes) {

		if ("cancel".equals(action)) {
			redirectAttributes.addFlashAttribute("infoMessage", "Edição cancelada.");
			return "redirect:/web/administracao/fornecedores";
		}

		FornecedorModel fornecedor = fornecedorDTO.getId() != null && fornecedorDTO.getId() > 0
				? fornecedorService.buscarFornecedorPorId(fornecedorDTO.getId()).orElseGet(FornecedorModel::new)
				: new FornecedorModel();

		try {
			atualizarFornecedorComDTO(fornecedor, fornecedorDTO);
		} catch (Exception e) {
			return "redirect:/web/administracao/fornecedores/fornecedor";
		}

		fornecedorService.salvarFornecedor(fornecedor);
		redirectAttributes.addFlashAttribute("successMessage", "Categoria salva com sucesso!");
		return "redirect:/web/administracao/fornecedores";
	}

	private void atualizarFornecedorComDTO(FornecedorModel fornecedor, FornecedorDTO fornecedorDTO) throws IOException {
		fornecedor.setNome(fornecedorDTO.getNome());
		fornecedor.setEmail(fornecedorDTO.getEmail());
		fornecedor.setTelefone(fornecedorDTO.getTelefone());
		fornecedor.setEndereco(fornecedorDTO.getEndereco());
	}

	@GetMapping("/administracao/fornecedores")
	public String listarFornecedores(Model model) {
		model.addAttribute("fornecedores", fornecedorService.listarFornecedores());
		return "administracaofornecedores";
	}

	@GetMapping("/administracao/fornecedores/excluir/{id}")
	public String excluirFornecedor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (!produtoRepository.findByFornecedorId(id).isEmpty()) {
			return "redirect:/web/administracao/fornecedores";
		}
		fornecedorService.deletarFornecedor(id);
		return "redirect:/web/administracao/fornecedores";
	}
}
