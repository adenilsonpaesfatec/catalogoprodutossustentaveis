package br.com.catalogoprodutossustentaveis.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.catalogoprodutossustentaveis.model.CategoriaModel;

/**
 * Repositório para a entidade {@link CategoriaModel}.
 * <p>Fornece métodos padrão de acesso a dados para operações CRUD em categorias.</p>
 * 
 * <p>Métodos adicionais podem ser definidos aqui para consultas personalizadas,
 * conforme necessário.</p>
 */
@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {

}
