package com.ufcg.psoft.controller;

import com.ufcg.psoft.model.Admin;
import com.ufcg.psoft.model.DTO.AdminDTO;
import com.ufcg.psoft.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import exceptions.ObjetoInvalidoException;


@Controller
public class AdminController {
    
    @Autowired
    AdminService adminService;

    public boolean fazerLoginAdmin(String cpf, String senha) throws ObjetoInvalidoException {
        validaLogin(cpf, senha);
        if (this.adminService.getLogin(cpf)) {
            throw new ObjetoInvalidoException("Admin com cpf: " + cpf + " ja esta logado");
        }
        Admin admin = this.adminService.findByCpf(cpf).get();
        this.adminService.setLogin(admin, true);
        return true;
    }

    public Admin criarAdmin(AdminDTO adminDTO) throws ObjetoInvalidoException {
        validaCampos(adminDTO);
        if (!this.adminService.findByCpf(adminDTO.getCpf()).isPresent()) {
            Admin admin = new Admin(adminDTO.getNome(), adminDTO.getCpf(), adminDTO.getSenha());
            this.adminService.saveAdmin(admin);
            return admin;
        }
        throw new ObjetoInvalidoException("O admin " + adminDTO.getNome() + " de CPF: "
        + adminDTO.getCpf() + " já está cadastrado.");
    }

    public String getLogin(String cpf) throws ObjetoInvalidoException {
        try {
            validaCpf(cpf);
        } catch (Exception e) {
            return e.getMessage();
        }
        return this.adminService.getLogin(cpf) == true ? "logado" : "Admin com cpf: " + cpf + " nao esta logado";
    }

    public boolean fazerLogoutAdmin(String cpf) throws ObjetoInvalidoException {
        validaCpf(cpf);
        if (!this.adminService.getLogin(cpf)) {
            throw new ObjetoInvalidoException("Admin com cpf: " + cpf + " nao esta logado");
        }
        Admin admin = this.adminService.findByCpf(cpf).get();
        this.adminService.setLogin(admin, false);
        return true;
    }

    private void validaLogin(String cpf, String senha) throws ObjetoInvalidoException {
        if (cpf == null || cpf.isEmpty()) {
            throw new ObjetoInvalidoException("O CPF não pode ser nulo ou vazio.");
        }
        if (cpf.length() < 11 || cpf.length() > 11) {
            throw new ObjetoInvalidoException("Quantidade de dígitos do CPF inválida.");
        }
        if (senha == null || senha.isEmpty()) {
            throw new ObjetoInvalidoException("A senha não pode ser nula ou vazia.");
        }
        if (!this.adminService.findByCpf(cpf).isPresent()) {
            throw new ObjetoInvalidoException("O admin com CPF: " + cpf + " não existe.");
        }
        if (!this.adminService.findBySenha(senha).isPresent()) {
            throw new ObjetoInvalidoException("Senha inválida.");
        }
        if (this.adminService.findByCpf(cpf).get() != this.adminService.findBySenha(senha).get()) {
            throw new ObjetoInvalidoException("Senha inválida.");
        }
    }

    private void validaCampos(AdminDTO adminDTO) throws ObjetoInvalidoException {
        if (adminDTO.getCpf() == null || adminDTO.getCpf().isEmpty()) {
            throw new ObjetoInvalidoException("O CPF não pode ser nulo ou vazio.");
        }
        if (adminDTO.getCpf().length() < 11 || adminDTO.getCpf().length() > 11) {
            throw new ObjetoInvalidoException("Quantidade de dígitos do CPF inválida.");
        }
        if (adminDTO.getNome() == null || adminDTO.getNome().isEmpty()) {
            throw new ObjetoInvalidoException("O nome não pode ser nulo ou vazio.");
        }
        if (adminDTO.getSenha() == null || adminDTO.getSenha().isEmpty()) {
            throw new ObjetoInvalidoException("A senha não pode ser nula ou vazia.");
        }
    }

    private void validaCpf(String cpf) throws ObjetoInvalidoException {
        if (cpf == null || cpf.isEmpty()) {
            throw new ObjetoInvalidoException("O CPF não pode ser nulo ou vazio.");
        }
        if (cpf.length() < 11 || cpf.length() > 11) {
            throw new ObjetoInvalidoException("Quantidade de dígitos do CPF inválida.");
        }
        if (!this.adminService.findByCpf(cpf).isPresent()) {
            throw new ObjetoInvalidoException("O admin com CPF: " + cpf + " não existe.");
        }
    }
}
