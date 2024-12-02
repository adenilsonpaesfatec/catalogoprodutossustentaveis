package br.com.catalogoprodutossustentaveis.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.catalogoprodutossustentaveis.model.ProdutoModel;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
	List<ProdutoModel> findByCategoriaId(Long categoriaId);
	List<ProdutoModel> findByFornecedorId(Long fornecedorId);
	
	@Query("SELECT p FROM ProdutoModel p WHERE (:categoriaId IS NULL OR p.categoria.id = :categoriaId) " +
		       "AND (:fornecedorId IS NULL OR p.fornecedor.id = :fornecedorId) " +
		       "AND (:precoMin IS NULL OR p.valor >= :precoMin) " +
		       "AND (:precoMax IS NULL OR p.valor <= :precoMax)")
		List<ProdutoModel> filtrarProdutos(@Param("categoriaId") Long categoriaId,
		                                   @Param("fornecedorId") Long fornecedorId,
		                                   @Param("precoMin") BigDecimal precoMin,
		                                   @Param("precoMax") BigDecimal precoMax);
	
    @Query("SELECT p FROM ProdutoModel p WHERE LOWER(p.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))")
    List<ProdutoModel> findByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);

}
