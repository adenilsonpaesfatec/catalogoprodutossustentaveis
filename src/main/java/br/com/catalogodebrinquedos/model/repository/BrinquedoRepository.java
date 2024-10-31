package br.com.catalogodebrinquedos.model.repository;

import br.com.catalogodebrinquedos.model.BrinquedoModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade {@link BrinquedoModel}.
 * <p>Fornece métodos para operações de acesso a dados relacionados a brinquedos.</p>
 */
@Repository
public interface BrinquedoRepository extends JpaRepository<BrinquedoModel, Long> {

	/**
	 * Busca uma lista de brinquedos associada a uma categoria específica pelo ID da
	 * categoria.
	 *
	 * @param categoriaId o identificador da categoria
	 * @return uma lista de {@link BrinquedoModel} associados ao ID da categoria
	 *         especificado
	 */
	List<BrinquedoModel> findByCategoriaId(Long categoriaId);
}
