package com.ufcg.psoft.controller;

import com.ufcg.psoft.model.DTO.*;
import com.ufcg.psoft.model.Venda;
import com.ufcg.psoft.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class RestApiController {

	@Autowired
	FacadeService facadeService;


	@RequestMapping(value = "/admin/{cpf}/produtos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarProdutos(@PathVariable("cpf") String cpf) throws Exception {
		return this.facadeService.listarProdutos(cpf);
	}

	@RequestMapping(value = "/admin/{cpf}/produtos/vencidos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarProdutosVencidos(@PathVariable("cpf") String cpf) throws Exception {
		return this.facadeService.listarProdutosVencidos(cpf);
	}

	@RequestMapping(value = "/admin/{cpf}/produtos/emfalta", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarProdutosEmFalta(@PathVariable("cpf") String cpf) throws Exception {
		return this.facadeService.listarProdutosEmFalta(cpf);
	}

	@RequestMapping(value = "/admin/{cpf}/produtos/embaixa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarProdutosEmBaixa(@PathVariable("cpf") String cpf) throws Exception {
		return this.facadeService.listarProdutosBaixa(cpf);
	}
	
	@RequestMapping(value = "/admin/{cpf}/produtos/{ordenacao}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarProdutosOrdenados(@PathVariable("cpf") String cpf, @PathVariable("ordenacao") String ordenacao) throws Exception {
		return this.facadeService.listarProdutosOrdenados(cpf, ordenacao);
	}

	@RequestMapping(value = "/admin/{cpf}/produto/", method = RequestMethod.POST)
	public ResponseEntity<?> criarProduto(@PathVariable("cpf") String cpf, @RequestBody ProdutoDTO produtoDTO) throws Exception {
		return this.facadeService.criarProduto(cpf, produtoDTO);
	}

	@RequestMapping(value = "/admin/{cpf}/produto/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> consultarProduto(@PathVariable("cpf") String cpf, @PathVariable("id") long id) throws Exception {
		return this.facadeService.consultarProduto(cpf, id);
	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> consultarProduto(@PathVariable("id") long id) throws Exception {
		return this.facadeService.consultarProduto(id);
	}

	@RequestMapping(value = "/admin/{cpf}/produto/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateProduto(@PathVariable("cpf") String cpf, @PathVariable("id") long id, @RequestBody ProdutoDTOAtualiza produtoDTOAtualiza) throws Exception {
		return this.facadeService.updateProduto(cpf, id, produtoDTOAtualiza);
	}

	@RequestMapping(value = "/admin/{cpf}/produto/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduto(@PathVariable("cpf") String cpf, @PathVariable("id") long id) throws Exception {
		return this.facadeService.deleteProduto(cpf, id);
	}

	@RequestMapping(value = "/admin/{cpf}/produto/{id}/lote", method = RequestMethod.POST)
	public ResponseEntity<?> criarLote(@PathVariable("cpf") String cpf, @PathVariable("id") long produtoId, @RequestBody LoteDTO loteDTO) throws Exception {
		return this.facadeService.criarLote(cpf, produtoId, loteDTO);
	}

	@RequestMapping(value = "/admin/{cpf}/lotes/vencimento/proximo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarLotesProximosDoVencimento(@PathVariable("cpf") String cpf) throws Exception {
		return this.facadeService.listarLotesProximosDoVencimento(cpf);
	}

	@RequestMapping(value = "/admin/{cpf}/lotes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarLotes(@PathVariable("cpf") String cpf) throws Exception {
		return this.facadeService.listarLotes(cpf);
	}

	@RequestMapping(value = "/admin/{cpf}/lotesembaixa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarLotesEmBaixa(@PathVariable("cpf") String cpf) throws Exception {
		return this.facadeService.listarLotesBaixa(cpf);
	}
	
	@RequestMapping(value = "/admin/{cpf}/categoria", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> criarCategoria(@PathVariable("cpf") String cpf, @RequestBody CategoriaDTO categoria) throws Exception {
		return this.facadeService.criarCategoria(cpf, categoria);
	}

	@RequestMapping(value = "/admin/{cpf}/categorias", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarCategorias(@PathVariable("cpf") String cpf) throws Exception {
		return this.facadeService.listarCategorias(cpf);
	}

	@RequestMapping(value = "/admin/{cpf}/categoria/{nome}/{desconto}", method = RequestMethod.PUT)
	public ResponseEntity<?> adicionarDesconto(@PathVariable("cpf") String cpf, @PathVariable("nome") String nome, @PathVariable("desconto") int desconto) throws Exception {
		return this.facadeService.adicionarDesconto(cpf, nome, desconto);
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> criarAdmin(@RequestBody AdminDTO adminDTO) throws Exception {
		return this.facadeService.criarAdmin(adminDTO);
	}

	@RequestMapping(value = "/login/{cpf}", method = RequestMethod.PUT)
	public ResponseEntity<?> fazerLoginAdmin(@PathVariable("cpf") String cpf, @RequestBody String senha) throws Exception {
		return this.facadeService.fazerLoginAdmin(cpf, senha);
	}

	@RequestMapping(value = "/logout/{cpf}", method = RequestMethod.PUT)
	public ResponseEntity<?> fazerLogoutAdmin(@PathVariable("cpf") String cpf) throws Exception {
		return this.facadeService.fazerLogoutAdmin(cpf);
	}

	@RequestMapping(value = "admin/{cpf}/venda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrarVenda(@PathVariable("cpf") String cpf , @RequestBody VendaDTO vendaDTO) throws Exception {
		return this.facadeService.registrarVenda(cpf, vendaDTO);
	}

	@RequestMapping(value = "admin/{cpf}/vendas/{ordenacao}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarVendasOrdenadas(@PathVariable("cpf") String cpf, @PathVariable("ordenacao") String ordenacao) throws Exception {
		return this.facadeService.listarVendasOrdenadas(cpf, ordenacao);
	}

	@RequestMapping(value = "admin/{cpf}/relatorio", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> relatorioMercado(@PathVariable("cpf") String cpf) throws Exception {
		return this.facadeService.relatorioMercado(cpf);
	}

	@RequestMapping(value = "admin/{cpf}/venda/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> cancelarVenda(@PathVariable("cpf") String cpf, @PathVariable("id") long id) throws Exception {
		return this.facadeService.cancelarVenda(cpf, id);
	}

}
