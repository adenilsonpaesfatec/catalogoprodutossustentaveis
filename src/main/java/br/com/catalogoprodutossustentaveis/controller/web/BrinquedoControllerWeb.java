package br.com.catalogoprodutossustentaveis.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.catalogoprodutossustentaveis.dto.BrinquedoDTO;
import br.com.catalogoprodutossustentaveis.model.BrinquedoModel;
import br.com.catalogoprodutossustentaveis.model.CategoriaModel;
import br.com.catalogoprodutossustentaveis.service.BrinquedoService;
import br.com.catalogoprodutossustentaveis.service.CategoriaService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Controller para gerenciar as operações de brinquedos na aplicação web.
 */
@Controller
@RequestMapping("/web")
public class BrinquedoControllerWeb {

	@Autowired
	private BrinquedoService brinquedoService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * Exibe a página inicial com uma lista aleatória de brinquedos.
	 *
	 * @param model O modelo que contém os atributos para a view.
	 * @return A página inicial 'home'.
	 */
	@GetMapping("/home")
	public String home(Model model) {
		List<BrinquedoModel> brinquedos = brinquedoService.listarBrinquedos();
		Collections.shuffle(brinquedos);
		List<BrinquedoModel> brinquedosAleatorios = brinquedos.stream().limit(6).toList();
		model.addAttribute("brinquedos", brinquedosAleatorios);
		return "home";
	}

	/**
	 * Exibe os detalhes de um brinquedo específico.
	 *
	 * @param id    O identificador do brinquedo.
	 * @param model O modelo que contém os atributos para a view.
	 * @return A página 'brinquedodetalhes' para visualização.
	 */
	@GetMapping("/catalogodebrinquedos/brinquedos/brinquedo/{id}")
	public String exibirDetalhesDoBrinquedo(@PathVariable Long id, Model model) {
		BrinquedoModel brinquedo = brinquedoService.buscarBrinquedoPorId(id).orElseThrow(() -> new RuntimeException(
				messageSource.getMessage("brinquedoControllerWeb.naoencontrado", null, Locale.getDefault())));
		model.addAttribute("brinquedo", brinquedo);
		return "brinquedodetalhes";
	}

	/**
	 * Exibe o formulário para a criação de um novo brinquedo.
	 *
	 * @param model O modelo que contém os atributos para a view.
	 * @return A página de formulário 'brinquedoform'.
	 */
	@GetMapping("/administracao/brinquedos/novobrinquedo")
	public String exibirFormularioNovoBrinquedo(Model model) {
		List<CategoriaModel> categorias = categoriaService.listarCategorias();
		model.addAttribute("categorias", categorias);
		model.addAttribute("brinquedo", new BrinquedoDTO());
		model.addAttribute("novo", true);
		return "brinquedoform";
	}

	/**
	 * Exibe o formulário para edição de um brinquedo existente.
	 *
	 * @param id    O identificador do brinquedo a ser editado.
	 * @param model O modelo que contém os atributos para a view.
	 * @return A página de formulário 'brinquedoform' preenchida com os dados do
	 *         brinquedo.
	 */
	@GetMapping("/administracao/brinquedos/editar/{id}")
	public String exibirFormularioEditarBrinquedo(@PathVariable Long id, Model model) {
		brinquedoService.buscarBrinquedoPorId(id).ifPresentOrElse(brinquedo -> {
			BrinquedoDTO brinquedoDTO = new BrinquedoDTO();
			brinquedoDTO.setId(brinquedo.getId());
			brinquedoDTO.setDescricao(brinquedo.getDescricao());
			brinquedoDTO.setCategoriaId(brinquedo.getCategoria().getId());
			brinquedoDTO.setMarca(brinquedo.getMarca());
			brinquedoDTO.setValor(brinquedo.getValor());
			brinquedoDTO.setDetalhes(brinquedo.getDetalhes());
			model.addAttribute("brinquedo", brinquedoDTO);
		}, () -> model.addAttribute("errorMessage",
				messageSource.getMessage("brinquedoControllerWeb.naoencontrado", null, Locale.getDefault())));
		List<CategoriaModel> categorias = categoriaService.listarCategorias();
		model.addAttribute("categorias", categorias);
		model.addAttribute("novo", false);
		return "brinquedoform";
	}

	/**
	 * Salva ou atualiza um brinquedo com base nos dados fornecidos no formulário.
	 *
	 * @param brinquedoDTO       DTO contendo os dados do brinquedo a serem salvos.
	 * @param redirectAttributes Atributos para mensagens de sucesso ou erro ao
	 *                           redirecionar.
	 * @return Redireciona para a página de administração de brinquedos.
	 */
	@PostMapping("/administracao/brinquedos/novobrinquedo")
	public String salvarBrinquedo(@ModelAttribute BrinquedoDTO brinquedoDTO, RedirectAttributes redirectAttributes) {
		BrinquedoModel brinquedo;
		if (brinquedoDTO.getId() != null && brinquedoDTO.getId() > 0) {
			Optional<BrinquedoModel> brinquedoExistente = brinquedoService.buscarBrinquedoPorId(brinquedoDTO.getId());
			brinquedo = brinquedoExistente.orElseGet(BrinquedoModel::new);
		} else {
			brinquedo = new BrinquedoModel();
		}
		if (brinquedoDTO.getDescricao() == null || brinquedoDTO.getDescricao().isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", messageSource
					.getMessage("brinquedoControllerWeb.descricao.obrigatoria", null, Locale.getDefault()));
			return "redirect:/web/administracao/brinquedos/novobrinquedo";
		}
		if (brinquedoDTO.getCategoriaId() == null) {
			redirectAttributes.addFlashAttribute("errorMessage", messageSource
					.getMessage("brinquedoControllerWeb.categoria.obrigatoria", null, Locale.getDefault()));
			return "redirect:/web/administracao/brinquedos/novobrinquedo";
		}
		if (brinquedoDTO.getValor() == null || brinquedoDTO.getValor().compareTo(BigDecimal.ZERO) <= 0) {
			redirectAttributes.addFlashAttribute("errorMessage",
					messageSource.getMessage("brinquedoControllerWeb.valor.invalido", null, Locale.getDefault()));
			return "redirect:/web/administracao/brinquedos/novobrinquedo";
		}
		try {
			atualizarBrinquedoComDTO(brinquedo, brinquedoDTO);
		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("errorMessage",
					messageSource.getMessage("brinquedoControllerWeb.erro.imagem", null, Locale.getDefault()));
			e.printStackTrace();
			return "redirect:/web/administracao/brinquedos/novobrinquedo";
		}
		brinquedoService.salvarBrinquedo(brinquedo);
		redirectAttributes.addFlashAttribute("successMessage",
				messageSource.getMessage("brinquedoControllerWeb.salvo.sucesso", null, Locale.getDefault()));
		return "redirect:/web/administracao/brinquedos";
	}

	/**
	 * Atualiza o objeto BrinquedoModel com os dados do BrinquedoDTO fornecido.
	 *
	 * @param brinquedo    O objeto BrinquedoModel a ser atualizado.
	 * @param brinquedoDTO DTO contendo os dados do brinquedo.
	 * @throws IOException Se houver um erro ao processar a imagem.
	 */
	private void atualizarBrinquedoComDTO(BrinquedoModel brinquedo, BrinquedoDTO brinquedoDTO) throws IOException {
		brinquedo.setDescricao(brinquedoDTO.getDescricao());
		brinquedo.setMarca(brinquedoDTO.getMarca());
		brinquedo.setValor(brinquedoDTO.getValor());
		brinquedo.setDetalhes(brinquedoDTO.getDetalhes());
		if (brinquedoDTO.getCategoriaId() != null) {
			CategoriaModel categoria = categoriaService.buscarCategoriaPorId(brinquedoDTO.getCategoriaId())
					.orElseThrow(() -> new RuntimeException(messageSource
							.getMessage("brinquedoControllerWeb.categoria.naoencontrada", null, Locale.getDefault())));
			brinquedo.setCategoria(categoria);
		}
		if (brinquedoDTO.getImagem() != null && !brinquedoDTO.getImagem().isEmpty()) {
			brinquedo.setImagem(brinquedoDTO.getImagem().getBytes());
		}
	}

	/**
	 * Exibe a página de administração com a lista de brinquedos.
	 *
	 * @param model O modelo que contém os atributos para a view.
	 * @return A página 'administracaobrinquedos' com a lista de brinquedos.
	 */
	@GetMapping("/administracao/brinquedos")
	public String listarBrinquedos(Model model) {
		List<BrinquedoModel> brinquedos = brinquedoService.listarBrinquedos();
		model.addAttribute("brinquedos", brinquedos);
		return "administracaobrinquedos";
	}

	/**
	 * Exibe a imagem de um brinquedo especificado pelo ID.
	 *
	 * @param id O identificador do brinquedo.
	 * @return Um ResponseEntity contendo a imagem em bytes e o cabeçalho de tipo
	 *         MIME.
	 */
	@GetMapping("/administracao/brinquedos/brinquedo/{id}")
	public ResponseEntity<byte[]> exibirImagemBrinquedo(@PathVariable Long id) {
		Optional<BrinquedoModel> brinquedo = brinquedoService.buscarBrinquedoPorId(id);
		if (brinquedo.isPresent() && brinquedo.get().getImagem() != null) {
			byte[] imagem = brinquedo.get().getImagem();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(org.springframework.http.MediaType.IMAGE_JPEG);
			return new ResponseEntity<>(imagem, headers, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Exclui um brinquedo pelo seu identificador.
	 *
	 * @param id                 O identificador do brinquedo a ser excluído.
	 * @param redirectAttributes Atributos para mensagens de sucesso ou erro ao
	 *                           redirecionar.
	 * @return Redireciona para a página de administração de brinquedos.
	 */
	@GetMapping("/administracao/brinquedos/excluir/{id}")
	public String excluirBrinquedo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		try {
			brinquedoService.deletarBrinquedo(id);
			redirectAttributes.addFlashAttribute("successMessage",
					messageSource.getMessage("brinquedoControllerWeb.excluido.sucesso", null, Locale.getDefault()));
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage",
					messageSource.getMessage("brinquedoControllerWeb.erro.excluir", null, Locale.getDefault()));
			e.printStackTrace();
		}
		return "redirect:/web/administracao/brinquedos";
	}

	/**
	 * Exibe a página "Sobre a equipe".
	 *
	 * @param model O modelo que contém os atributos para a view.
	 * @return A página 'sobre'.
	 */
	@GetMapping("/sobre")
	public String sobre(Model model) {
		return "sobre";
	}
}
