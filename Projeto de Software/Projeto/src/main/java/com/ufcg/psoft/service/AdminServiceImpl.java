package com.ufcg.psoft.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.model.Admin;
import com.ufcg.psoft.model.Venda;
import com.ufcg.psoft.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<Admin> findAllAdmins() {
        return this.adminRepository.findAll();
    }

    @Override
    public Optional<Admin> findByCpf(String cpf) {
        return this.adminRepository.findByCpf(cpf);
    }

    @Override
    public void saveAdmin(Admin admin) {
        this.adminRepository.save(admin);
    }

    @Override
    public void setLogin(Admin admin, boolean login) {
        admin.setLogin(login);
        this.saveAdmin(admin);
    }

    @Override
    public boolean getLogin(String cpf) {
        Admin admin = this.adminRepository.findByCpf(cpf).get();
        return admin.getLogin();
    }

    @Override
    public Optional<Admin> findBySenha(String senha) {
        return this.adminRepository.findBySenha(senha);
    }
}
