package com.ufcg.psoft.service;


import com.ufcg.psoft.model.DTO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface FacadeService {

    ResponseEntity<?> listarProdutos(String cpf) throws Exception;

    ResponseEntity<?> listarProdutosOrdenados(String cpf, String ordenacao) throws Exception;

    ResponseEntity<?> criarProduto(String cpf, ProdutoDTO produtoDTO) throws Exception;

    ResponseEntity<?> consultarProduto(String cpf, long id) throws Exception;

    ResponseEntity<?> consultarProduto(long id) throws Exception;

    ResponseEntity<?> updateProduto(String cpf, long id, ProdutoDTOAtualiza produtoDTOAtualiza) throws Exception;

    ResponseEntity<?> deleteProduto(String cpf, long id) throws Exception;

    ResponseEntity<?> criarLote(String cpf, long produtoId, LoteDTO loteDTO) throws Exception;

    ResponseEntity<?> listarLotes(String cpf) throws Exception;

    ResponseEntity<?> criarCategoria(String cpf, CategoriaDTO categoria) throws Exception;
  
    ResponseEntity<?> listarLotesProximosDoVencimento(String cpf) throws Exception;

    ResponseEntity<?> listarCategorias(String cpf) throws Exception;

    ResponseEntity<?> adicionarDesconto(String cpf, String nome, int desconto) throws Exception;

    ResponseEntity<?> criarAdmin(AdminDTO adminDTO) throws Exception;

    ResponseEntity<?> fazerLoginAdmin(String cpf, String senha) throws Exception;

    ResponseEntity<?> fazerLogoutAdmin(String cpf) throws Exception;

    ResponseEntity<?> registrarVenda(String cpf, VendaDTO vendaDTO) throws Exception;

    ResponseEntity<?> listarProdutosVencidos(String cpf) throws Exception;

    ResponseEntity<?> listarProdutosEmFalta(String cpf) throws Exception;

    ResponseEntity<?> listarVendasOrdenadas(String cpf, String ordenacao) throws Exception;

    ResponseEntity<?> relatorioMercado(String cpf) throws Exception;

    ResponseEntity<?> cancelarVenda(String cpf, long id) throws Exception;

	ResponseEntity<?> listarLotesBaixa(String cpf) throws Exception;

	ResponseEntity<?> listarProdutosBaixa(String cpf) throws Exception;
}
