package br.com.catalogodebrinquedos.model.repository;

import br.com.catalogodebrinquedos.model.Brinquedo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrinquedoRepository extends JpaRepository<Brinquedo, Integer> {
}