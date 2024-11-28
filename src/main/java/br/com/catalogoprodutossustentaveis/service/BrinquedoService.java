package br.com.catalogoprodutossustentaveis.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.catalogoprodutossustentaveis.model.BrinquedoModel;
import br.com.catalogoprodutossustentaveis.model.repository.BrinquedoRepository;

/**
 * Serviço para gerenciamento das operações relacionadas aos brinquedos no
 * sistema.
 * <p>Inclui métodos para salvar, buscar, listar e deletar brinquedos.</p>
 */
@Service
public class BrinquedoService {

	@Autowired
	private BrinquedoRepository brinquedoRepository;

	@Autowired
	private MessageSource messageSource;

	/**
	 * Salva um brinquedo no repositório, aplicando validações de campos
	 * obrigatórios e valores.
	 *
	 * @param brinquedo o brinquedo a ser salvo
	 * @return o brinquedo salvo com todas as informações persistidas
	 * @throws IllegalArgumentException se o brinquedo ou seus campos obrigatórios
	 *                                  forem nulos ou inválidos
	 */
	@Transactional
	public BrinquedoModel salvarBrinquedo(BrinquedoModel brinquedo) {
		Assert.notNull(brinquedo, messageSource.getMessage("brinquedoService.categoria.notnull", null, Locale.getDefault()));
		return brinquedoRepository.save(brinquedo);
	}

	/**
	 * Busca um brinquedo pelo seu identificador único.
	 *
	 * @param id o identificador do brinquedo a ser buscado
	 * @return um Optional contendo o brinquedo encontrado ou vazio, caso não exista
	 * @throws IllegalArgumentException se o ID fornecido for nulo
	 */
	public Optional<BrinquedoModel> buscarBrinquedoPorId(Long id) {
		Assert.notNull(id, messageSource.getMessage("brinquedoService.id.notnull", null, Locale.getDefault()));
		return brinquedoRepository.findById(id);
	}

	/**
	 * Busca todos os brinquedos associados a uma categoria específica.
	 *
	 * @param categoriaId o identificador da categoria
	 * @return uma lista de brinquedos associados à categoria fornecida
	 * @throws IllegalArgumentException se o ID da categoria for nulo
	 */
	public List<BrinquedoModel> buscarBrinquedosPorCategoria(Long categoriaId) {
		Assert.notNull(categoriaId,
				messageSource.getMessage("brinquedoService.categoria.id.notnull", null, Locale.getDefault()));
		return brinquedoRepository.findByCategoriaId(categoriaId);
	}

	/**
	 * Lista todos os brinquedos cadastrados no sistema.
	 *
	 * @return uma lista com todos os brinquedos cadastrados
	 */
	public List<BrinquedoModel> listarBrinquedos() {
		return brinquedoRepository.findAll();
	}

	/**
	 * Deleta um brinquedo com base no seu identificador.
	 *
	 * @param id o identificador do brinquedo a ser deletado
	 * @throws IllegalArgumentException se o ID fornecido for nulo
	 */
	@Transactional
	public void deletarBrinquedo(Long id) {
		Assert.notNull(id, messageSource.getMessage("brinquedoService.id.notnull", null, Locale.getDefault()));
		brinquedoRepository.deleteById(id);
	}

    public long contarBrinquedos() {
        return brinquedoRepository.count();
    }
    
}	
