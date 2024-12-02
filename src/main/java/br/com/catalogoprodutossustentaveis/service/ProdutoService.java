package br.com.catalogoprodutossustentaveis.service;

import java.math.BigDecimal;
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
    
    public List<ProdutoModel> filtrarProdutos(Long categoriaId, Long fornecedorId, BigDecimal precoMin, BigDecimal precoMax, String descricao) {
        List<ProdutoModel> produtos = produtoRepository.findAll();

        if (descricao != null && !descricao.isEmpty()) {
            produtos = produtoRepository.findByDescricaoContainingIgnoreCase(descricao);
        }

        if (categoriaId != null) {
            produtos = produtos.stream()
                    .filter(produto -> produto.getCategoria().getId().equals(categoriaId))
                    .toList();
        }

        if (fornecedorId != null) {
            produtos = produtos.stream()
                    .filter(produto -> produto.getFornecedor() != null && produto.getFornecedor().getId().equals(fornecedorId))
                    .toList();
        }

        if (precoMin != null) {
            produtos = produtos.stream()
                    .filter(produto -> produto.getValor().compareTo(precoMin) >= 0)
                    .toList();
        }

        if (precoMax != null) {
            produtos = produtos.stream()
                    .filter(produto -> produto.getValor().compareTo(precoMax) <= 0)
                    .toList();
        }

        return produtos;
    }

}
