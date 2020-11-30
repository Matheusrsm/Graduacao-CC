package com.ufcg.psoft.util;

import com.ufcg.psoft.model.*;
import com.ufcg.psoft.repository.*;
import exceptions.ObjetoInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DummyData {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    LoteRepository loteRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostConstruct  //Povoa o banco de dados a medida em que a aplicação é iniciada. Como o banco está "create-drop" nao a necessidade de comentar após uma vez ja inicializado.
    public void saveProdutos() throws ObjetoInvalidoException {

        List<Produto> produtoList = new ArrayList<>();
        List<Lote> loteList = new ArrayList<>();
        List<Categoria> categoriaList = new ArrayList<>();
        List<Admin> adminList = new ArrayList<>();

        Admin admin1 = new Admin("Rogerio Ferreira", "12345678901", "123");
        Admin admin2 = new Admin("Amanda Melo", "12345678902", "admin2");
        Admin admin3 = new Admin("Eric Hugo", "12345678903", "1020admin");

        adminList.add(admin1);
        adminList.add(admin2);
        adminList.add(admin3);

        admin1.setLogin(true);

        for (Admin admin : adminList) {
            this.adminRepository.save(admin);
        }

        Categoria categoria1 = new Categoria("Limpeza");
        Categoria categoria2 = new Categoria("Perecíveis");
        Categoria categoria3 = new Categoria("Higiene Pessoal");
        Categoria categoria4 = new Categoria("Mercearia");

        categoriaList.add(categoria1);
        categoriaList.add(categoria2);
        categoriaList.add(categoria3);
        categoriaList.add(categoria4);

        for (Categoria categoria : categoriaList)
            this.categoriaRepository.save(categoria);

        Produto produto1 = new Produto("Leite Integral", "87654321-B", "Parmalat", categoria2, new BigDecimal(5.60));
        Produto produto2 = new Produto("Arroz Integral", "87654322-B", "Tio Joao", categoria2, new BigDecimal(4.30));
        Produto produto3 = new Produto("Sabao em Po", "87654323-B", "OMO", categoria1, new BigDecimal(8.75));
        Produto produto4 = new Produto("Agua Sanitaria", "87654324-C", "Dragao", categoria1, new BigDecimal(4.65));
        Produto produto5 = new Produto("Creme Dental", "87654325-C", "Colgate", categoria3, new BigDecimal(3.70));
        Produto produto6 = new Produto("Bolacha Maria", "87654326-B", "Comida LTDA", categoria4, new BigDecimal(1.75));
        Produto produto7 = new Produto("Bolacha Cream-Cracker", "87654327-B", "Comida LTDA", categoria4, new BigDecimal(3.00));
        Produto produto8 = new Produto("Sabonete Líquido", "876543240-B", "Unilever", categoria3, new BigDecimal(9.00));

        produtoList.add(produto1);
        produtoList.add(produto2);
        produtoList.add(produto3);
        produtoList.add(produto4);
        produtoList.add(produto5);
        produtoList.add(produto6);
        produtoList.add(produto7);
        produtoList.add(produto8);

        try {
			loteList.add(new Lote(produto1, 10, "20/12/2020"));
			loteList.add(new Lote(produto2, 15, "20/12/2021"));
			loteList.add(new Lote(produto3, 20, "10/03/2021"));
			loteList.add(new Lote(produto4, 8, "13/05/2022"));
			loteList.add(new Lote(produto5, 5, "29/07/2023"));
			loteList.add(new Lote(produto6, 50, "16/04/2021"));
			loteList.add(new Lote(produto7, 100, "05/02/2021"));
			loteList.add(new Lote(produto8, 100, "05/12/2020"));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

        for (Produto produto : produtoList){
            produto.tornaDisponivel();
            this.produtoRepository.save(produto);
        }

        for (Lote lote : loteList){
            this.loteRepository.save(lote);
        }

    }

}

