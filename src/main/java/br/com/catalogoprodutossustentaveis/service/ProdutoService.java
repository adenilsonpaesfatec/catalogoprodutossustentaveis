package br.com.catalogoprodutossustentaveis.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.catalogoprodutossustentaveis.model.ProdutoModel;
import br.com.catalogoprodutossustentaveis.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public ProdutoModel salvarProduto(ProdutoModel produto) {
		return produtoRepository.save(produto);
	}

	public Optional<ProdutoModel> buscarProdutoPorId(Long id) {
		return produtoRepository.findById(id);
	}

	public List<ProdutoModel> buscarProdutosPorCategoria(Long categoriaId) {
		return produtoRepository.findByCategoriaId(categoriaId);
	}

	public List<ProdutoModel> listarProdutos() {
		return produtoRepository.findAll();
	}

	@Transactional
	public void deletarProduto(Long id) {
		produtoRepository.deleteById(id);
	}

	public long contarProdutos() {
		return produtoRepository.count();
	}

	public Page<ProdutoModel> filtrarProdutosPaginados(int page, int size, Long categoriaId, Long fornecedorId,
			BigDecimal precoMin, BigDecimal precoMax, String descricao) {
		Pageable pageable = PageRequest.of(page, size);
		return produtoRepository.filtrarProdutosPaginados(categoriaId, fornecedorId, precoMin, precoMax, descricao,
				pageable);
	}
	
	public List<Map<String, Object>> buscarProdutosMaisBemAvaliados() {
	    return produtoRepository.buscarProdutosMaisBemAvaliados();
	}

}
