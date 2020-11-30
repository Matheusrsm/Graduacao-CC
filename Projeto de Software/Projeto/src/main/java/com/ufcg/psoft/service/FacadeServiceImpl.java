package com.ufcg.psoft.service;

import com.ufcg.psoft.controller.AdminController;
import com.ufcg.psoft.controller.CategoriaController;
import com.ufcg.psoft.controller.LoteController;
import com.ufcg.psoft.controller.ProdutoController;
import com.ufcg.psoft.controller.VendaController;
import com.ufcg.psoft.model.*;
import com.ufcg.psoft.model.DTO.*;
import com.ufcg.psoft.util.CustomErrorType;
import exceptions.ObjetoInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FacadeServiceImpl implements FacadeService {

    @Autowired
    ProdutoController produtoController;

    @Autowired
    LoteController loteController;

    @Autowired
    CategoriaController categoriaController;

    @Autowired
    AdminController adminController;

    @Autowired
    VendaController vendaController;

    @Override
    public ResponseEntity<?> listarProdutosBaixa(String cpf) throws Exception {
        this.checkProdutosVencidos();
        if (this.getLogin(cpf).equals("logado")) {
            List<Produto> produtosBaixa = new ArrayList<>();
            for (Produto produto: this.produtoController.listarProdutos()) {
                int totalItens = 0;
                boolean anyLote = false;
                for (Lote lote: this.loteController.listarLotesBaixa()) {
                    if (lote.getProduto().equals(produto)) {
                        anyLote = true;
                        totalItens += lote.getNumeroDeItens();
                    }
                }
                if (anyLote && totalItens < 15)
                    produtosBaixa.add(produto);
            }
            return produtosBaixa.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(produtosBaixa, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> listarLotesBaixa(String cpf) throws Exception {
        this.checkProdutosVencidos();
        if (this.getLogin(cpf).equals("logado")) {
            List<Lote>lotesBaixa = this.loteController.listarLotesBaixa();
            return lotesBaixa.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(lotesBaixa, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> listarProdutosVencidos(String cpf) throws Exception {
        this.checkProdutosVencidos();
        if (this.getLogin(cpf).equals("logado")) {
            List<Produto> produtosVencidos = this.produtoController.listarProdutosVencidos();
            return produtosVencidos.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(produtosVencidos, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> listarProdutosEmFalta(String cpf) throws Exception {
        if (this.getLogin(cpf).equals("logado")) {
            List<ProdutoDTO> produtosEmFalta = this.produtoController.listarProdutosEmFalta();
            return produtosEmFalta.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(produtosEmFalta, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> listarProdutos(String cpf) throws Exception {
        this.checkProdutosVencidos();
        if (this.getLogin(cpf).equals("logado")) {
            List<Produto> produtos = this.produtoController.listarProdutos();
            return produtos.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(produtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> listarProdutosOrdenados(String cpf, String ordenacao) throws Exception {
        if (this.getLogin(cpf).equals("logado")) {
            List<Produto> produtosOrdenados = this.produtoController.listarProdutosOrdenados(ordenacao);
            return new ResponseEntity<>(produtosOrdenados, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> criarProduto(String cpf, ProdutoDTO produtoDTO) throws Exception {
        if (this.getLogin(cpf).equals("logado")) {
            produtoController.validarProduto(produtoDTO);
            Optional<Produto> produto = produtoController.findByCodigoDeBarra(produtoDTO.getCodigoBarra());
            if (!produto.isPresent()) {
                Produto produtoCriado =  this.produtoController.criarProduto(criaProdutoComCategoria(produtoDTO));
                return new ResponseEntity<>(produtoCriado, HttpStatus.CREATED);
            }
            else
                return new ResponseEntity<>(new CustomErrorType("O produto " + produtoDTO.getNome() + " do fabricante " + produtoDTO.getFabricante() + " ja esta cadastrado!"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> consultarProduto(String cpf, long id) throws Exception {
        this.checkProdutoVencido(id);
        if (this.getLogin(cpf).equals("logado")) {
            Produto produto = this.produtoController.consultarProduto(id);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> consultarProduto(long id) throws Exception {
        this.checkProdutoVencido(id);
        return this.produtoController.consultarProdutoUsuario(id);
    }

    @Override
    public ResponseEntity<?> updateProduto(String cpf, long id, ProdutoDTOAtualiza produtoDTOAtualiza) throws Exception {
        if (this.getLogin(cpf).equals("logado")) {
            this.produtoController.validarId(id);
            Produto produto = this.produtoController.consultarProduto(id);
            String nomeCategoria = produtoDTOAtualiza.getCategoria();
            if (!categoriaController.categoriaExiste(nomeCategoria))
                this.categoriaController.criarCategoria(nomeCategoria);
            Produto produtoAtualizado = this.produtoController.updateProduto(produto, produtoDTOAtualiza, id, this.categoriaController.retornaCategoria(nomeCategoria));
            if (produtoAtualizado != null)
                return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
            else
                return new ResponseEntity<>(new CustomErrorType("Produto inválido"), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> deleteProduto(String cpf, long id) throws Exception {
        if (this.getLogin(cpf).equals("logado")){
            boolean produtoDeletado = this.produtoController.deleteProduto(id);
            return produtoDeletado ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(new CustomErrorType("Produto com o id: " + id + " nao encontrado"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> criarLote(String cpf, long produtoId, LoteDTO loteDTO) throws Exception {
        if (this.getLogin(cpf).equals("logado")) {
            Optional<Produto> produtoOptional = this.produtoController.findById(produtoId);
            if (produtoOptional.isPresent()) {
                if (this.formatoDataValido(loteDTO.getDataDeValidade())) {
                    if (loteDTO.getNumeroDeItens() > 0) {
                        Produto produto = produtoOptional.get();
                        Lote lote = new Lote(produto, loteDTO.getNumeroDeItens(), loteDTO.getDataDeValidade());
                        lote = this.loteController.criarLote(lote);
                        this.atualizaSituacaoLote(produto);
                        return new ResponseEntity<>(lote, HttpStatus.CREATED);
                    }
                    return new ResponseEntity<>(new CustomErrorType("Numero de itens do lote invalido"), HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(new CustomErrorType("Formato da data invalido"), HttpStatus.BAD_REQUEST);
            }
            else
                return new ResponseEntity<>(new CustomErrorType("Produto com o id: " + produtoId + " nao encontrado"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> listarLotes(String cpf) throws Exception {
        this.checkProdutosVencidos();
        if (this.getLogin(cpf).equals("logado")) {
            List<Lote> lotes = this.loteController.listarLotes();
            return lotes.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT):
                    new ResponseEntity<>(lotes, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> listarLotesProximosDoVencimento(String cpf) throws Exception {
        if (this.getLogin(cpf).equals("logado")) {
            List<Lote> lotesProximosDoVencimento = this.loteController.listarLotesProximosDoVencimento();
            return lotesProximosDoVencimento.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT): new ResponseEntity<>(lotesProximosDoVencimento, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> criarCategoria(String cpf, CategoriaDTO categoria) throws Exception {
        if (this.getLogin(cpf).equals("logado")) {
            Categoria categoriaNova = this.categoriaController.criarCategoria(categoria.getNome());
            return categoriaNova == null ? new ResponseEntity<>(new CustomErrorType("A categoria " + categoria.getNome() + " já está cadastrada!"), HttpStatus.CONFLICT) : new ResponseEntity<>(categoriaNova, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> adicionarDesconto(String cpf, String nome, int desconto) throws Exception {
        if (this.getLogin(cpf).equals("logado"))
            return this.categoriaController.adicionarDesconto(nome, desconto);
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> listarCategorias(String cpf) throws Exception {
        if (this.getLogin(cpf).equals("logado")) {
            List<Categoria> categorias = this.categoriaController.listarCategorias();
            return categorias.isEmpty() ? new ResponseEntity<>(new CustomErrorType("Não existe nenhuma categoria cadastrada!"), HttpStatus.NOT_FOUND) : new ResponseEntity<>(categorias, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> criarAdmin(AdminDTO adminDTO) {
        try {
            Admin criarAdmin = this.adminController.criarAdmin(adminDTO);
            if (criarAdmin != null)
                return new ResponseEntity<>(criarAdmin, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomErrorType(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.LOCKED);
    }

    @Override
    public ResponseEntity<?> fazerLoginAdmin(String cpf, String senha) {
        try {
            boolean login = this.adminController.fazerLoginAdmin(cpf, senha);
            if (login)
                return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomErrorType(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.LOCKED);
    }

    @Override
    public ResponseEntity<?> fazerLogoutAdmin(String cpf) {
        try {
            boolean logout = this.adminController.fazerLogoutAdmin(cpf);
            if (logout)
                return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomErrorType(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.LOCKED);
    }

    @Override
    public ResponseEntity<?> registrarVenda(String cpf, VendaDTO vendaDTO)
            throws Exception {
        if (this.getLogin(cpf).equals("logado")) {
            try {
                double preco = this.produtoController.precoVenda(vendaDTO.getProdutos());
                int qtd = this.vendaController.qtdItensVenda(vendaDTO.getProdutos().values());
                this.vendaController.validaVenda(vendaDTO.getProdutos(), preco, qtd);

                verificaProdutosVenda(vendaDTO.getProdutos());

                verificaQtdProdutos(vendaDTO.getProdutos());

                List<Item> itens = criaItens(vendaDTO.getProdutos());

                Venda venda = new Venda(itens, preco, qtd);
                return this.vendaController.registrarVenda(venda);
            } catch (Exception e) {
                return new ResponseEntity<>(new CustomErrorType(e.getMessage()), HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> listarVendasOrdenadas(String cpf, String ordenacao) throws Exception {
        if (this.getLogin(cpf).equals("logado"))
            return this.vendaController.listarVendasOrdenadas(ordenacao);
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> relatorioMercado(String cpf) throws Exception {
        if(this.getLogin(cpf).equals("logado")){
            List<Lote> lotes = this.loteController.listarLotes();
            List<Produto> produtos = this.produtoController.listarProdutos();
            List<Venda> vendas = this.vendaController.getVendas();
            Relatorio relatorio = new Relatorio(lotes, produtos, vendas);
            return new ResponseEntity<>(relatorio, HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> cancelarVenda(String cpf, long id) throws Exception {
        if (this.getLogin(cpf).equals("logado")) {
            Optional<Venda> vendaOptional = this.vendaController.retornaVenda(id);
            if(vendaOptional.isPresent()) {
                Venda venda = vendaOptional.get();
                atualizaProdutos(venda);
                this.vendaController.deleteVenda(venda.getId());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else
                return new ResponseEntity<>(new CustomErrorType("A venda com id: " + id + " não foi encontrada"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new CustomErrorType(this.getLogin(cpf)), HttpStatus.UNAUTHORIZED);
    }

    private String getLogin(String cpf) throws Exception {
        return this.adminController.getLogin(cpf);
    }

    private boolean formatoDataValido(String datastr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            sdf.parse(datastr);
            return true;
        } catch (ParseException parse) {
            return false;
        }
    }

    private void atualizaSituacaoLote(Produto produto) throws Exception {
        if (produto.isIndisponivel())
            this.produtoController.mudaSituacao(produto);
        this.atualizaValidade(produto.getId());
        this.checkProdutoVencido(produto.getId());
    }

    private List<Item> criaItens(Map<Long, Integer> produtos) throws Exception {
        List<Item> itens = new ArrayList<>();
        for (long id : produtos.keySet()) {
            Produto produto = this.produtoController.consultarProduto(id);
            Item item = new Item(produto, produtos.get(id));
            this.vendaController.salvarItem(item);
            itens.add(item);
        }
        return itens;
    }

    private void verificaProdutosVenda(Map<Long, Integer> produtos) throws Exception {
        for (Long id : produtos.keySet()) {
            Produto produto = this.produtoController.consultarProduto(id);
            if (produto.getSituacao() == Produto.INDISPONIVEL)
                throw new ObjetoInvalidoException("O produto com id: " + id + " está indisponível para venda");
        }
    }

    private void verificaQtdProdutos(Map<Long, Integer> produtos) throws Exception {
        Set<Produto> listProdutos = this.produtoController.produtosVenda(produtos.keySet());
        for (Produto produto : listProdutos) {
            int itens = produtos.get(produto.getId());
            Lote lote = this.loteController.retornaLote(produto);
            if (lote.getNumeroDeItens() - itens < 0) {
                throw new ObjetoInvalidoException("O lote do produto de id: " + produto.getId() + " não tem quantidade suficiente para venda");
            } else {
                lote.setNumeroDeItens(lote.getNumeroDeItens() - itens);
                if (lote.getNumeroDeItens() == 0)
                    this.produtoController.mudaSituacao(produto);
            }
        }
    }

    private Produto criaProdutoComCategoria(ProdutoDTO produtoDTO) {
        Produto produto;
        if (categoriaController.categoriaExiste(produtoDTO.getCategoria())) {
            Categoria categoriaExistente = this.categoriaController.retornaCategoria(produtoDTO.getCategoria());
            produto = new Produto(produtoDTO.getNome(), produtoDTO.getCodigoBarra(), produtoDTO.getFabricante(), categoriaExistente);
        }
        else {
            this.categoriaController.criarCategoria(produtoDTO.getCategoria());
            Categoria categoriaCriada = this.categoriaController.retornaCategoria(produtoDTO.getCategoria());
            produto = new Produto(produtoDTO.getNome(), produtoDTO.getCodigoBarra(), produtoDTO.getFabricante(), categoriaCriada);
        }
        return produto;
    }

    private void checkProdutosVencidos() throws Exception {
        for(Produto produto: this.produtoController.listarProdutos()) {
            this.checkProdutoVencido(produto.getId());
        }
    }

    private void checkProdutoVencido(long id) throws Exception {
        if (this.calcularProdutoVencido(id)) {
            produtoController.setProdutoVencido(id, true);
        }
    }

    private boolean calcularProdutoVencido(long id) throws Exception {
        boolean anyLote = false;
        Optional<Produto> produto = this.produtoController.findById(id);
        if(produto.isPresent() && !this.produtoController.isProdutoVencido(id)){
            Produto p = produto.get();
            List<Lote> lotes = this.loteController.listarLotes();
            for(Lote lote: lotes) {
                if(lote.getProduto().equals(p)) {
                    anyLote = true;
                    if(!lote.isLoteVencido())
                        return false;
                }
            }
            return anyLote;
        }
        return false;
    }

    private void atualizaValidade(long id) throws Exception {
        if (!this.calcularProdutoVencido(id))
            this.produtoController.setProdutoVencido(id, false);
    }

    private void atualizaProdutos(Venda venda) {
        for (Item item : venda.getItens()) {
            Produto produto = item.getProduto();
            produto.tornaDisponivel();
            this.produtoController.salvarProduto(produto);
            Lote lote = this.loteController.atualizaQtdDeProdutos(item.getQuantidade(), produto);
            this.loteController.salvarLote(lote);
        }
    }
}
