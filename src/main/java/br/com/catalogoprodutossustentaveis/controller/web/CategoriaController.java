package br.com.catalogoprodutossustentaveis.controller.web;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.catalogoprodutossustentaveis.dto.CategoriaDTO;
import br.com.catalogoprodutossustentaveis.model.ProdutoModel;
import br.com.catalogoprodutossustentaveis.repository.CategoriaRepository;
import br.com.catalogoprodutossustentaveis.repository.ProdutoRepository;
import br.com.catalogoprodutossustentaveis.model.CategoriaModel;
import br.com.catalogoprodutossustentaveis.service.CategoriaService;

@Controller
@RequestMapping("/web")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/administracao/categorias/novacategoria")
    public String exibirFormularioNovaCategoria(Model model) {
        model.addAttribute("categoria", new CategoriaModel());
        model.addAttribute("novo", true);
        return "categoriaform";
    }

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

    @PostMapping("/administracao/categorias/novacategoria")
    public String salvarCategoria(@ModelAttribute CategoriaDTO categoriaDTO, @RequestParam String action, RedirectAttributes redirectAttributes) {
        
    	if ("cancel".equals(action)) {
            redirectAttributes.addFlashAttribute("infoMessage", "Edição cancelada.");
            return "redirect:/web/administracao/categorias";
        }
    	
    	CategoriaModel categoria = categoriaDTO.getId() != null && categoriaDTO.getId() > 0
                ? categoriaService.buscarCategoriaPorId(categoriaDTO.getId()).orElseGet(CategoriaModel::new)
                : new CategoriaModel();

        try {
            atualizarCategoriaComDTO(categoria, categoriaDTO);
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar a imagem.");
            return "redirect:/web/administracao/categorias";
        }

        categoriaService.salvarCategoria(categoria);
        redirectAttributes.addFlashAttribute("successMessage", "Categoria salva com sucesso!");
        return "redirect:/web/administracao/categorias";
    }

    private void atualizarCategoriaComDTO(CategoriaModel categoria, CategoriaDTO categoriaDTO) throws IOException {
        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());
        if (categoriaDTO.getImagem() != null && !categoriaDTO.getImagem().isEmpty()) {
            categoria.setImagem(categoriaDTO.getImagem().getBytes());
        }
    }

    @GetMapping("/administracao/categorias")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "administracaocategorias";
    }

    @GetMapping("/categorias/categoria/imagem/{id}")
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

    @GetMapping("/administracao/categorias/excluir/{id}")
    public String excluirCategoria(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            if (!produtoRepository.findByCategoriaId(id).isEmpty()) {
                redirectAttributes.addFlashAttribute("confirmMessage", "Erro: Não é possível excluir a categoria, pois ela contém produtos associados.");
                return "redirect:/web/administracao/categorias";
            }
            categoriaService.deletarCategoria(id);
            redirectAttributes.addFlashAttribute("confirmMessage", "Categoria excluída com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("confirmMessage", "Erro ao excluir a categoria.");
        }
        return "redirect:/web/administracao/categorias";
    }

    @GetMapping("/catalogodeprodutos")
    public String listaCategoriasNoCatalogoDeProdutos(Model model) {
        List<CategoriaModel> categorias = categoriaService.listarCategorias();
        model.addAttribute("categorias", categorias);
        return "catalogodeprodutos";
    }

    @GetMapping("/catalogodeprodutos/categorias/categoria/{id}")
    public String exibirProdutosPorCategoria(@PathVariable Long id, Model model) {
        CategoriaModel categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
        List<ProdutoModel> produtos = produtoRepository.findByCategoriaId(id);
        model.addAttribute("produtos", produtos);
        model.addAttribute("categoriaNome", categoria.getNome());
        return "produtosporcategoria";
    }
}
