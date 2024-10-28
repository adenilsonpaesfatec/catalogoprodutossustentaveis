package br.com.catalogodebrinquedos.service;

import br.com.catalogodebrinquedos.model.CategoriaModel;
import br.com.catalogodebrinquedos.model.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Método para salvar uma nova categoria
    public CategoriaModel salvarCategoria(CategoriaModel categoria) {
        return categoriaRepository.save(categoria);
    }

    // Método para buscar uma categoria por ID
    public Optional<CategoriaModel> buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    // Método para listar todas as categorias
    public List<CategoriaModel> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // Método para deletar uma categoria por ID
    public void deletarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
