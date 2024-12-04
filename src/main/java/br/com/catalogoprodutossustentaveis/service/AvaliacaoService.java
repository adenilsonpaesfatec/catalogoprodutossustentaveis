package br.com.catalogoprodutossustentaveis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.catalogoprodutossustentaveis.model.AvaliacaoModel;
import br.com.catalogoprodutossustentaveis.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoModel salvarAvaliacao(AvaliacaoModel avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public List<AvaliacaoModel> buscarAvaliacoesPorProduto(Long produtoId) {
        return avaliacaoRepository.findByProdutoId(produtoId);
    }
}
