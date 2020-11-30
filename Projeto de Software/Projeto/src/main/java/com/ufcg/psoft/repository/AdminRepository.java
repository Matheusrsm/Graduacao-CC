package com.ufcg.psoft.repository;

import java.util.Optional;

import com.ufcg.psoft.model.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

    Optional<Admin> findByCpf(String cpf);

    Optional<Admin> findBySenha(String senha);
}
