package br.com.catalogoprodutossustentaveis.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.catalogoprodutossustentaveis.model.FornecedorModel;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorModel, Long> {
}
