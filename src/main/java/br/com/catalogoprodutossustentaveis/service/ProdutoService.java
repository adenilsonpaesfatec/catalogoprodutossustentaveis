package br.com.catalogoprodutossustentaveis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
}
