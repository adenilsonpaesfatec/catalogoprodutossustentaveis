package br.com.catalogoprodutossustentaveis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.catalogoprodutossustentaveis.model.CategoriaModel;
import br.com.catalogoprodutossustentaveis.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaModel salvarCategoria(CategoriaModel categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<CategoriaModel> buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public List<CategoriaModel> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public void deletarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    public long contarCategorias() {
        return categoriaRepository.count();
    }
}
