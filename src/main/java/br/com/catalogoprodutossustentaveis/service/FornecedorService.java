package br.com.catalogoprodutossustentaveis.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.catalogoprodutossustentaveis.model.FornecedorModel;
import br.com.catalogoprodutossustentaveis.model.ProdutoModel;
import br.com.catalogoprodutossustentaveis.repository.FornecedorRepository;
import br.com.catalogoprodutossustentaveis.repository.ProdutoRepository;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;
    
    public FornecedorModel salvarFornecedor(FornecedorModel fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Optional<FornecedorModel> buscarFornecedorPorId(Long id) {
        return fornecedorRepository.findById(id);
    }

    public List<FornecedorModel> listarFornecedores() {
        return fornecedorRepository.findAll();
    }

    public void deletarFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
    }
    
    public long contarFornecedores() {
        return fornecedorRepository.count();
    }
}
