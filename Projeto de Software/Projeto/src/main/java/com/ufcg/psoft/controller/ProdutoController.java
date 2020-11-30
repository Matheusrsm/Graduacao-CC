package com.ufcg.psoft.controller;

import com.ufcg.psoft.model.Categoria;
import com.ufcg.psoft.model.DTO.ProdutoDTO;
import com.ufcg.psoft.model.DTO.ProdutoDTOAtualiza;
import com.ufcg.psoft.model.DTO.ProdutoDTODescricaoUsuario;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.comparator.produto.*;
import com.ufcg.psoft.service.ProdutoService;
import com.ufcg.psoft.util.CustomErrorType;
import exceptions.ObjetoInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.*;

@Controller
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    public List<Produto> listarProdutos(){
        return this.produtoService.findAllProdutos();
    }

    public List<ProdutoDTO> listarProdutosEmFalta() {
        List<Produto> produtos = this.produtoService.findAllProdutos();
        List<ProdutoDTO> produtosEmFalta = new ArrayList<ProdutoDTO>();
        for (Produto produto : produtos) {
            if (produto.getSituacao() == Produto.INDISPONIVEL)
                produtosEmFalta.add(this.criaProdutoDTO(produto));
        }

        return produtosEmFalta;
    }

    public List<Produto> listarProdutosVencidos(){
        List<Produto> produtos = this.produtoService.findAllProdutos();
        List<Produto> produtosIndisponiveis = new ArrayList<>();
        for (Produto p: produtos) {
            if(p.isProdutoVencido()) {
                produtosIndisponiveis.add(p);
            }
        }

        return produtosIndisponiveis;
    }

    public List<Produto> listarProdutosOrdenados(String ordenacao) throws ObjetoInvalidoException {
        List<Produto> produtos = this.produtoService.findAllProdutos();
        Comparator comparator;
        switch (ordenacao.toLowerCase()) {
            case "nome": comparator = new OrdenaProdutoPorNome(); break;
            case "categoria": comparator = new OrdenaProdutoPorCategoria(); break;
            case "fabricante": comparator = new OrdenaProdutoPorFabricante(); break;
            case "preco": comparator = new OrdenaProdutoPorPreco(); break;
            case "situacao": comparator = new OrdenaProdutoPorSituacao(); break;
            default: throw new ObjetoInvalidoException("Tipo de ordenação: " + ordenacao + " é inválida");
        }

        Collections.sort(produtos, comparator);
        return produtos;
    }

    public Produto criarProduto(Produto produto) {
        return this.produtoService.saveProduto(produto);
    }

    public Produto consultarProduto(long id) throws ObjetoInvalidoException {
        validarId(id);
        Optional<Produto> produto = this.produtoService.findById(id);
        validaProdutoEncontrado(produto, id);
        return produto.get();
    }

    private void validaProdutoEncontrado(Optional<Produto> produto, long id) throws ObjetoInvalidoException {
        if(!produto.isPresent()){
            throw new ObjetoInvalidoException("Produto com o id: " + id +  " nao encontrado");
        }
    }

    public ResponseEntity<?> consultarProdutoUsuario(long id) throws ObjetoInvalidoException {
        validarId(id);

        Optional<Produto> produto = this.produtoService.findById(id);

        if(produto.isPresent()) {
            Produto produtoAtual = produto.get();
            if (produtoAtual.isIndisponivel() || produtoAtual.isProdutoVencido())
                return new ResponseEntity<ProdutoDTO>(this.criaProdutoDTO(produtoAtual), HttpStatus.OK);

            ProdutoDTODescricaoUsuario produtoDescricao = new ProdutoDTODescricaoUsuario(produtoAtual.getNome(), produtoAtual.getPreco(), produtoAtual.getFabricante(), produtoAtual.getCategoria().getNome(), produtoAtual.getSituacao());
            return new ResponseEntity<ProdutoDTODescricaoUsuario>(produtoDescricao, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Produto com o id: " + id + " nao encontrado"), HttpStatus.NOT_FOUND);
    }

    private ProdutoDTO criaProdutoDTO(Produto produto) {
        return new ProdutoDTO(produto.getNome(), produto.getCodigoBarra(), produto.getFabricante(), produto.getCategoria().getNome());
    }

    public Produto updateProduto(Produto produto, ProdutoDTOAtualiza produtoDTOAtualiza, long id, Categoria categoria) throws ObjetoInvalidoException{
        if(produto != null){
            Produto produtoAtual = produto;
            Produto produtoAtualizado = this.validaAtualizaCampos(produtoAtual, produtoDTOAtualiza, categoria);
            return produtoAtualizado;
        }
        else{
            return null;
        }
    }

    public boolean deleteProduto(long id) throws ObjetoInvalidoException{
        validarId(id);

        Optional<Produto> optionalProduto = this.produtoService.findById(id);
        if(optionalProduto.isPresent()){
            this.produtoService.deleteProdutoById(id);
            return true;
        }
        else{
            return false;
        }
    }

    public void mudaSituacao(Produto produto) {
        if (produto.getSituacao() == Produto.INDISPONIVEL)
            produto.tornaDisponivel();
        else
            produto.tornaIndisponivel();
        this.produtoService.saveProduto(produto);
    }

    public Optional<Produto> findById(long id) throws ObjetoInvalidoException {
        validarId(id);
        return this.produtoService.findById(id);
    }

    public void validarId(long id) throws ObjetoInvalidoException {
        if(id < 1){
            throw new ObjetoInvalidoException("Id do produto não pode ser menor do que 1.");
        }
    }

    public Produto validaAtualizaCampos(Produto produtoAtual, ProdutoDTOAtualiza produtoDTOAtualiza, Categoria categoria) {
        if(produtoDTOAtualiza.getNome() != null){
            produtoAtual.setNome(produtoDTOAtualiza.getNome());
        }
        if(produtoDTOAtualiza.getPreco() != null){
            produtoAtual.setPreco(produtoDTOAtualiza.getPreco());
        }
        if(produtoDTOAtualiza.getCodigoBarra() != null){
            produtoAtual.setCodigoBarra(produtoDTOAtualiza.getCodigoBarra());
        }
        if(produtoDTOAtualiza.getFabricante() != null){
            produtoAtual.setFabricante(produtoDTOAtualiza.getFabricante());
        }
        if(produtoDTOAtualiza.getCategoria() != null){
            produtoAtual.setCategoria(categoria);
        }

        return produtoAtual;
    }

    public void validarProduto(ProdutoDTO produtoDTO) throws ObjetoInvalidoException {
        if(produtoDTO.getNome() == null){
            throw new ObjetoInvalidoException("Nome do produto nao pode ser nulo ou vazio.");
        }
        if(produtoDTO.getCodigoBarra() == null){
            throw new ObjetoInvalidoException("Codigo de barra do produto nao pode ser nulo ou vazio.");
        }
        if(produtoDTO.getFabricante() == null){
            throw new ObjetoInvalidoException("Fabricante do produto nao pode ser nulo ou vazio.");
        }
        if(produtoDTO.getCategoria() == null){
            throw new ObjetoInvalidoException("Categoria do produto nao pode ser nula ou vazia.");
        }
    }

    public BigDecimal getPreco(Produto produto) {
        return this.produtoService.getPreco(produto);
    }

    public BigDecimal getDesconto(Produto produto) {
        return this.produtoService.getDesconto(produto);
    }

    public double precoVenda(Map<Long, Integer> produtos) throws ObjetoInvalidoException {
        if (produtos.isEmpty() || produtos == null)
            throw new ObjetoInvalidoException("Lista de produtos não pode ser vazia ou nula.");

        double precoTotal = 0;

        for (Long l : produtos.keySet()) {
            Produto p = this.findById(l).get();
            double preco = this.getPreco(p).doubleValue();
            Integer qtd = produtos.get(l);
            preco *= qtd;
            double desconto = this.getDesconto(p).doubleValue();
            if (desconto > 0)
                preco -= preco * desconto;
            precoTotal += preco;
        }
        return precoTotal;
    }

    public Set<Produto> produtosVenda(Set<Long> id) throws ObjetoInvalidoException {
        Set<Produto> produtos = new HashSet<Produto>();

        for (Long l : id) {
            Produto produto = this.findById(l).get();
            produtos.add(produto);
        }

        return produtos;
    }

    public void setProdutoVencido(long id, boolean vencido) throws ObjetoInvalidoException{
        this.validarId(id);
        Optional<Produto> produto = this.produtoService.findById(id);
        if(produto.isPresent()){
            Produto produtoAtualizado = produto.get();
            produtoAtualizado.setProdutoVencido(vencido);
            this.produtoService.updateProduto(produtoAtualizado);
        }
    }

    public boolean isProdutoVencido(long id) throws ObjetoInvalidoException {
        this.validarId(id);
        Optional<Produto> produto = this.produtoService.findById(id);
        if(produto.isPresent()){
            return produto.get().isProdutoVencido();
        }
        return false;
    }

    public void salvarProduto(Produto produto) {
        this.produtoService.saveProduto(produto);
    }

    public Optional<Produto> findByCodigoDeBarra(String codigoBarra){
        return this.produtoService.findByCodigoDeBarras(codigoBarra);
    }

}

