package br.com.catalogodebrinquedos.controller.web;

import br.com.catalogodebrinquedos.dto.CategoriaDTO;
import br.com.catalogodebrinquedos.model.BrinquedoModel;
import br.com.catalogodebrinquedos.model.CategoriaModel;
import br.com.catalogodebrinquedos.model.repository.BrinquedoRepository;
import br.com.catalogodebrinquedos.model.repository.CategoriaRepository;
import br.com.catalogodebrinquedos.service.CategoriaService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/web")
public class CategoriaControllerWeb {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private BrinquedoRepository brinquedoRepository;

	// Método GET para exibir o formulário de criação de categoria
	@GetMapping("/administracao/categorias/novacategoria")
	public String exibirFormularioNovaCategoria(Model model) {
		model.addAttribute("categoria", new CategoriaModel());
		model.addAttribute("novo", true); // Definindo como "Nova Categoria"
		return "categoriaform";
	}

	// Método POST para salvar a nova categoria ou atualizar a existente
	@PostMapping("/administracao/categorias/novacategoria")
	public String salvarCategoria(@ModelAttribute CategoriaDTO categoriaDTO) {
		CategoriaModel categoria;

		if (categoriaDTO.getId() != null && categoriaDTO.getId() > 0) {
			// Caso o ID seja válido, estamos editando uma categoria existente
			Optional<CategoriaModel> categoriaExistente = categoriaService.buscarCategoriaPorId(categoriaDTO.getId());

			if (categoriaExistente.isPresent()) {
				categoria = categoriaExistente.get();
				categoria.setNome(categoriaDTO.getNome());
				categoria.setDescricao(categoriaDTO.getDescricao());

				try {
					// Verifica se a imagem foi fornecida
					if (categoriaDTO.getImagem() != null && !categoriaDTO.getImagem().isEmpty()) {
						// Atualizar a imagem somente se uma nova foi carregada
						categoria.setImagem(categoriaDTO.getImagem().getBytes());
					}
				} catch (IOException e) {
					e.printStackTrace();
					// Lidar com erro adequadamente
				}
			} else {
				// Se a categoria não existir no banco, tratamos como uma nova criação
				categoria = new CategoriaModel();
				categoria.setNome(categoriaDTO.getNome());
				categoria.setDescricao(categoriaDTO.getDescricao());
				try {
					if (categoriaDTO.getImagem() != null && !categoriaDTO.getImagem().isEmpty()) {
						categoria.setImagem(categoriaDTO.getImagem().getBytes());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			// Se não houver ID, é uma criação de nova categoria
			categoria = new CategoriaModel();
			categoria.setNome(categoriaDTO.getNome());
			categoria.setDescricao(categoriaDTO.getDescricao());
			try {
				if (categoriaDTO.getImagem() != null && !categoriaDTO.getImagem().isEmpty()) {
					categoria.setImagem(categoriaDTO.getImagem().getBytes());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		categoriaService.salvarCategoria(categoria);
		return "redirect:/web/administracao/categorias";
	}

	// Método GET para exibir o formulário de edição de uma categoria existente
	@GetMapping("/administracao/categorias/editar/{id}")
	public String exibirFormularioEditarCategoria(@PathVariable Long id, Model model) {
		categoriaService.buscarCategoriaPorId(id).ifPresent(categoria -> {
			CategoriaDTO categoriaDTO = new CategoriaDTO();
			categoriaDTO.setId(categoria.getId());
			categoriaDTO.setNome(categoria.getNome());
			categoriaDTO.setDescricao(categoria.getDescricao());
			// Não setamos imagem aqui pois não podemos enviar os bytes de volta para a view
			model.addAttribute("categoria", categoriaDTO);
		});
		model.addAttribute("novo", false);
		return "categoriaform";
	}

	// Método GET para listar todas as categorias
	@GetMapping("/administracao/categorias")
	public String listarCategorias(Model model) {
		model.addAttribute("categorias", categoriaService.listarCategorias());
		return "administracaocategorias";
	}

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

	@GetMapping("/catalogodebrinquedos")
	public String catalogoDeBrinquedos(Model model) {
		List<CategoriaModel> categorias = categoriaService.listarCategorias();
		model.addAttribute("categorias", categorias);
		return "catalogodebrinquedos";
	}

	@GetMapping("/catalogodebrinquedos/categorias/categoria/{id}")
	public String exibirBrinquedosPorCategoria(@PathVariable Long id, Model model) {
		CategoriaModel categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
		List<BrinquedoModel> brinquedos = brinquedoRepository.findByCategoriaId(id);
		model.addAttribute("brinquedos", brinquedos);
		model.addAttribute("categoriaNome", categoria.getNome());
		return "brinquedosporcategoria";
	}

	@GetMapping("/administracao/categorias/excluir/{id}")
	public String excluirCategoria(@PathVariable Long id) {
		categoriaService.deletarCategoria(id);
		return "redirect:/web/administracao/categorias";
	}

}
