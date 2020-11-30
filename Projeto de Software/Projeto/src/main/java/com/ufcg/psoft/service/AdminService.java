package com.ufcg.psoft.service;

import java.util.List;
import java.util.Optional;
import com.ufcg.psoft.model.Admin;
import com.ufcg.psoft.model.Venda;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    List<Admin> findAllAdmins();

    Optional<Admin> findByCpf(String cpf);

    void saveAdmin(Admin admin);

    void setLogin(Admin admin, boolean login);

    boolean getLogin(String cpf);

    Optional<Admin> findBySenha(String senha);
}
