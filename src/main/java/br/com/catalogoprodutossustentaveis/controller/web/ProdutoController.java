package br.com.catalogoprodutossustentaveis.controller.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
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

import br.com.catalogoprodutossustentaveis.dto.ProdutoDTO;
import br.com.catalogoprodutossustentaveis.model.ProdutoModel;
import br.com.catalogoprodutossustentaveis.model.CategoriaModel;
import br.com.catalogoprodutossustentaveis.model.FornecedorModel;
import br.com.catalogoprodutossustentaveis.service.ProdutoService;
import br.com.catalogoprodutossustentaveis.service.CategoriaService;
import br.com.catalogoprodutossustentaveis.service.FornecedorService;

@Controller
@RequestMapping("/web")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping("/home")
    public String home(Model model) {
        List<ProdutoModel> produtos = produtoService.listarProdutos();
        Collections.shuffle(produtos);
        model.addAttribute("produtos", produtos.stream().limit(6).toList());
        return "home";
    }

    @GetMapping("/catalogodeprodutos/produtos/produto/{id}")
    public String exibirDetalhesDoProduto(@PathVariable Long id, Model model) {
        ProdutoModel produto = produtoService.buscarProdutoPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        model.addAttribute("produto", produto);
        return "produtodetalhes";
    }

    @GetMapping("/administracao/produtos/novoproduto")
    public String exibirFormularioNovoProduto(Model model) {
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("fornecedores", fornecedorService.listarFornecedores());
        model.addAttribute("produto", new ProdutoDTO());
        model.addAttribute("novo", true);
        return "produtoform";
    }

    @GetMapping("/administracao/produtos/editar/{id}")
    public String exibirFormularioEditarProduto(@PathVariable Long id, Model model) {
        produtoService.buscarProdutoPorId(id).ifPresent(produto -> {
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setId(produto.getId());
            produtoDTO.setDescricao(produto.getDescricao());
            produtoDTO.setCategoriaId(produto.getCategoria().getId());
            produtoDTO.setMarca(produto.getMarca());
            produtoDTO.setValor(produto.getValor());
            produtoDTO.setDetalhes(produto.getDetalhes());
            produtoDTO.setFornecedorId(produto.getFornecedor() != null ? produto.getFornecedor().getId() : null);
            model.addAttribute("produto", produtoDTO);
        });
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("fornecedores", fornecedorService.listarFornecedores());
        model.addAttribute("novo", false);
        return "produtoform";
    }

    @PostMapping("/administracao/produtos/novoproduto")
    public String salvarProduto(@ModelAttribute ProdutoDTO produtoDTO, @RequestParam String action, RedirectAttributes redirectAttributes) throws IOException {
        
    	if ("cancel".equals(action)) {
            redirectAttributes.addFlashAttribute("infoMessage", "Edição cancelada.");
            return "redirect:/web/administracao/produtos";
        }
    	
    	ProdutoModel produto = produtoDTO.getId() != null
                ? produtoService.buscarProdutoPorId(produtoDTO.getId()).orElse(new ProdutoModel())
                : new ProdutoModel();
        atualizarProdutoComDTO(produto, produtoDTO);
        produtoService.salvarProduto(produto);

        redirectAttributes.addFlashAttribute("successMessage", "Produto salvo com sucesso!");
        return "redirect:/web/administracao/produtos";
    }

    private void atualizarProdutoComDTO(ProdutoModel produto, ProdutoDTO produtoDTO) throws IOException {
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setMarca(produtoDTO.getMarca());
        produto.setValor(produtoDTO.getValor());
        produto.setDetalhes(produtoDTO.getDetalhes());
        if (produtoDTO.getCategoriaId() != null) {
            CategoriaModel categoria = categoriaService.buscarCategoriaPorId(produtoDTO.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
            produto.setCategoria(categoria);
        }
        if (produtoDTO.getFornecedorId() != null) {
            FornecedorModel fornecedor = fornecedorService.buscarFornecedorPorId(produtoDTO.getFornecedorId())
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado."));
            produto.setFornecedor(fornecedor);
        }
        if (produtoDTO.getImagem() != null && !produtoDTO.getImagem().isEmpty()) {
            produto.setImagem(produtoDTO.getImagem().getBytes());
        }
    }

    @GetMapping("/administracao/produtos")
    public String listarProdutos(@RequestParam(required = false) Long categoriaId,
                                  @RequestParam(required = false) Long fornecedorId,
                                  @RequestParam(required = false) BigDecimal precoMin,
                                  @RequestParam(required = false) BigDecimal precoMax,
                                  @RequestParam(required = false) String descricao,
                                  Model model) {
        List<ProdutoModel> produtos = produtoService.filtrarProdutos(categoriaId, fornecedorId, precoMin, precoMax, descricao);
        model.addAttribute("produtos", produtos);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("fornecedores", fornecedorService.listarFornecedores());
        return "administracaoprodutos";
    }

    @GetMapping("/produtos/produto/imagem/{id}")
    public ResponseEntity<byte[]> exibirImagemProduto(@PathVariable Long id) {
        Optional<ProdutoModel> produto = produtoService.buscarProdutoPorId(id);
        if (produto.isPresent() && produto.get().getImagem() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(produto.get().getImagem(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/administracao/produtos/excluir/{id}")
    public String excluirProduto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            produtoService.deletarProduto(id);
            redirectAttributes.addFlashAttribute("successMessage", "Produto excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir o produto.");
        }
        return "redirect:/web/administracao/produtos";
    }

    @GetMapping("/sobre")
    public String sobre() {
        return "sobre";
    }
}
