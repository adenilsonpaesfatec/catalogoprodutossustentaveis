package br.com.catalogoprodutossustentaveis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.catalogoprodutossustentaveis.model.ProdutoModel;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
	List<ProdutoModel> findByCategoriaId(Long categoriaId);
	List<ProdutoModel> findByFornecedorId(Long fornecedorId);
}
