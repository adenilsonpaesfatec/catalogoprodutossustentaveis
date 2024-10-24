package br.com.catalogodebrinquedos.model.repository;

import br.com.catalogodebrinquedos.model.BrinquedoModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrinquedoRepository extends JpaRepository<BrinquedoModel, Long> {
	List<BrinquedoModel> findByCategoriaId(Long categoriaId);
}