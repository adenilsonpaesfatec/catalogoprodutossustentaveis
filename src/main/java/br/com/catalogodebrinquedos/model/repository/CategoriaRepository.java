package br.com.catalogodebrinquedos.model.repository;

import br.com.catalogodebrinquedos.model.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade CategoriaModel. Fornece métodos padrão de acesso
 * a dados para operações CRUD em categorias.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {
	// Métodos adicionais podem ser definidos aqui para consultas personalizadas,
	// conforme necessário.
}
