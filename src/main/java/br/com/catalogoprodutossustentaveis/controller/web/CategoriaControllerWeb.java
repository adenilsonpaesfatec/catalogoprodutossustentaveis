package br.com.catalogoprodutossustentaveis.controller.web;

import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.catalogoprodutossustentaveis.dto.CategoriaDTO;
import br.com.catalogoprodutossustentaveis.model.BrinquedoModel;
import br.com.catalogoprodutossustentaveis.model.CategoriaModel;
import br.com.catalogoprodutossustentaveis.model.repository.BrinquedoRepository;
import br.com.catalogoprodutossustentaveis.model.repository.CategoriaRepository;
import br.com.catalogoprodutossustentaveis.service.CategoriaService;

/**
 * Controller responsável pelo gerenciamento das categorias no contexto de
 * administração e exibição do catálogo de brinquedos.
 */
@Controller
@RequestMapping("/web")
@Validated
public class CategoriaControllerWeb {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private BrinquedoRepository brinquedoRepository;

	@Autowired
	private MessageSource messageSource;

	/**
	 * Exibe o formulário para criação de uma nova categoria.
	 *
	 * @param model o modelo para adicionar atributos
	 * @return o nome da página de formulário de categoria
	 */
	@GetMapping("/administracao/categorias/novacategoria")
	public String exibirFormularioNovaCategoria(Model model) {
		model.addAttribute("categoria", new CategoriaModel());
		model.addAttribute("novo", true);
		return "categoriaform";
	}

	/**
	 * Exibe o formulário para edição de uma categoria existente.
	 *
	 * @param id    o ID da categoria a ser editada
	 * @param model o modelo para adicionar atributos
	 * @return o nome da página de formulário de categoria
	 */
	@GetMapping("/administracao/categorias/editar/{id}")
	public String exibirFormularioEditarCategoria(@PathVariable Long id, Model model) {
		categoriaService.buscarCategoriaPorId(id).ifPresent(categoria -> {
			CategoriaDTO categoriaDTO = new CategoriaDTO();
			categoriaDTO.setId(categoria.getId());
			categoriaDTO.setNome(categoria.getNome());
			categoriaDTO.setDescricao(categoria.getDescricao());
			model.addAttribute("categoria", categoriaDTO);
		});
		model.addAttribute("novo", false);
		return "categoriaform";
	}

	/**
	 * Salva uma nova categoria ou atualiza uma existente.
	 *
	 * @param categoriaDTO       os dados da categoria a serem salvos
	 * @param redirectAttributes para mensagens flash
	 * @param bindingResult      resultados de validação de formulário
	 * @return redireciona para a lista de categorias
	 */
	@PostMapping("/administracao/categorias/novacategoria")
	public String salvarCategoria(@ModelAttribute @Valid CategoriaDTO categoriaDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errorMessage",
					messageSource.getMessage("categoriaControllerWeb.erro.validacao", null, Locale.getDefault()));
			return "redirect:/web/administracao/categorias/categoria";
		}

		CategoriaModel categoria;
		if (categoriaDTO.getId() != null && categoriaDTO.getId() > 0) {
			Optional<CategoriaModel> categoriaExistente = categoriaService.buscarCategoriaPorId(categoriaDTO.getId());
			categoria = categoriaExistente.orElseGet(CategoriaModel::new);
		} else {
			categoria = new CategoriaModel();
		}

		try {
			atualizarCategoriaComDTO(categoria, categoriaDTO);
		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("errorMessage",
					messageSource.getMessage("categoriaControllerWeb.erro.imagem", null, Locale.getDefault()));
			e.printStackTrace();
			return "redirect:/web/administracao/categorias/categoria";
		}

		categoriaService.salvarCategoria(categoria);
		redirectAttributes.addFlashAttribute("successMessage",
				messageSource.getMessage("categoriaControllerWeb.salvo.sucesso", null, Locale.getDefault()));
		return "redirect:/web/administracao/categorias";
	}

	/**
	 * Atualiza o objeto CategoriaModel com dados do DTO, incluindo a imagem.
	 *
	 * @param categoria    a categoria a ser atualizada
	 * @param categoriaDTO os dados do DTO
	 * @throws IOException se houver um erro ao processar a imagem
	 */
	private void atualizarCategoriaComDTO(CategoriaModel categoria, CategoriaDTO categoriaDTO) throws IOException {
		categoria.setNome(categoriaDTO.getNome());
		categoria.setDescricao(categoriaDTO.getDescricao());
		if (categoriaDTO.getImagem() != null && !categoriaDTO.getImagem().isEmpty()) {
			categoria.setImagem(categoriaDTO.getImagem().getBytes());
		}
	}

	/**
	 * Lista todas as categorias para administração.
	 *
	 * @param model o modelo para adicionar atributos
	 * @return o nome da página de lista de categorias
	 */
	@GetMapping("/administracao/categorias")
	public String listarCategorias(Model model) {
		model.addAttribute("categorias", categoriaService.listarCategorias());
		return "administracaocategorias";
	}

	/**
	 * Exibe a imagem da categoria pelo seu ID.
	 *
	 * @param id o ID da categoria
	 * @return a imagem da categoria ou status 404 se não encontrada
	 */
	@GetMapping("/administracao/categorias/categoria/{id}")
	public ResponseEntity<byte[]> exibirImagemCategoria(@PathVariable Long id) {
		Optional<CategoriaModel> categoria = categoriaService.buscarCategoriaPorId(id);
		if (categoria.isPresent() && categoria.get().getImagem() != null) {
			byte[] imagem = categoria.get().getImagem();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(org.springframework.http.MediaType.IMAGE_JPEG);
			return new ResponseEntity<>(imagem, headers, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Exclui uma categoria pelo seu ID após verificar se não há brinquedos associados.
	 *
	 * @param id                 o ID da categoria a ser excluída
	 * @param redirectAttributes para mensagens flash
	 * @return redireciona para a lista de categorias
	 */
	@GetMapping("/administracao/categorias/excluir/{id}")
	public String excluirCategoria(@PathVariable Long id, RedirectAttributes redirectAttributes) {
	    try {
	        // Verifica se há brinquedos associados à categoria
	        if (!brinquedoRepository.findByCategoriaId(id).isEmpty()) {
	            redirectAttributes.addFlashAttribute("confirmMessage",
	                    messageSource.getMessage("categoriaControllerWeb.erro.excluir.associacao", null, Locale.getDefault()));
	            return "redirect:/web/administracao/categorias";
	        }

	        // Exclui a categoria se não houver brinquedos associados
	        categoriaService.deletarCategoria(id);
	        redirectAttributes.addFlashAttribute("successMessage",
	                messageSource.getMessage("categoriaControllerWeb.excluido.sucesso", null, Locale.getDefault()));
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("errorMessage",
	                messageSource.getMessage("categoriaControllerWeb.erro.excluir", null, Locale.getDefault()));
	        e.printStackTrace();
	    }
	    return "redirect:/web/administracao/categorias";
	}


	/**
	 * Lista as categorias no catálogo de brinquedos.
	 *
	 * @param model o modelo para adicionar atributos
	 * @return o nome da página do catálogo de brinquedos
	 */
	@GetMapping("/catalogodebrinquedos")
	public String listaCategoriasNoCatalogoDeBrinquedos(Model model) {
		List<CategoriaModel> categorias = categoriaService.listarCategorias();
		model.addAttribute("categorias", categorias);
		return "catalogodebrinquedos";
	}

	/**
	 * Exibe brinquedos de uma categoria específica.
	 *
	 * @param id    o ID da categoria
	 * @param model o modelo para adicionar atributos
	 * @return o nome da página de brinquedos por categoria
	 */
	@GetMapping("/catalogodebrinquedos/categorias/categoria/{id}")
	public String exibirBrinquedosPorCategoria(@PathVariable Long id, Model model) {
		CategoriaModel categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSource
				.getMessage("categoriaControllerWeb.erro.categoria.nao.encontrada", null, Locale.getDefault())));
		List<BrinquedoModel> brinquedos = brinquedoRepository.findByCategoriaId(id);
		model.addAttribute("brinquedos", brinquedos);
		model.addAttribute("categoriaNome", categoria.getNome());
		return "brinquedosporcategoria";
	}
}
