package br.com.catalogodebrinquedos.service;

import br.com.catalogodebrinquedos.model.CategoriaModel;
import br.com.catalogodebrinquedos.model.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Serviço responsável pelo gerenciamento das operações relacionadas às
 * categorias no sistema de catálogo de brinquedos.
 */
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private MessageSource messageSource;

	/**
	 * Salva uma categoria no repositório.
	 *
	 * @param categoria a categoria a ser salva
	 * @return a categoria salva com todas as informações persistidas
	 * @throws IllegalArgumentException se a categoria fornecida for nula
	 */
	public CategoriaModel salvarCategoria(CategoriaModel categoria) {
		Assert.notNull(categoria,
				messageSource.getMessage("categoriaService.categoria.notnull", null, Locale.getDefault()));
		return categoriaRepository.save(categoria);
	}

	/**
	 * Busca uma categoria pelo seu identificador único.
	 *
	 * @param id o identificador da categoria a ser buscada
	 * @return um Optional contendo a categoria encontrada ou vazio, caso não exista
	 * @throws IllegalArgumentException se o ID fornecido for nulo
	 */
	public Optional<CategoriaModel> buscarCategoriaPorId(Long id) {
		Assert.notNull(id, messageSource.getMessage("categoriaService.id.notnull", null, Locale.getDefault()));
		return categoriaRepository.findById(id);
	}

	/**
	 * Lista todas as categorias cadastradas no sistema.
	 *
	 * @return uma lista com todas as categorias cadastradas
	 */
	public List<CategoriaModel> listarCategorias() {
		return categoriaRepository.findAll();
	}

	/**
	 * Deleta uma categoria com base no seu identificador.
	 *
	 * @param id o identificador da categoria a ser deletada
	 * @throws IllegalArgumentException se o ID fornecido for nulo
	 */
	public void deletarCategoria(Long id) {
		Assert.notNull(id, messageSource.getMessage("categoriaService.id.notnull", null, Locale.getDefault()));
		categoriaRepository.deleteById(id);
	}
}
