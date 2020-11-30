package com.ufcg.psoft.repository;

import java.util.Optional;

import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {

    Optional<Lote> findByProduto(Produto produto);
}
