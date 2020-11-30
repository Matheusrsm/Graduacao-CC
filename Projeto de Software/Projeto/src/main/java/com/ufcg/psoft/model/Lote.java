package com.ufcg.psoft.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = Lote.TB_LOTE)
public class Lote {

    public static final String TB_LOTE = "TB_LOTE";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Produto produto;

    private int numeroDeItens;

    private Date dataDeValidade;

    public Lote() {
        this.id = 0;
    }

    public Lote(Produto produto, int numeroDeItens, String dataDeValidade) throws ParseException {
        super();
        this.produto = produto;
        this.numeroDeItens = numeroDeItens;
        this.dataDeValidade = converterData(dataDeValidade);
    }

    public Lote(long id, Produto produto, int numeroDeItens, String dataDeValidade) throws ParseException {
        this.id = id;
        this.produto = produto;
        this.numeroDeItens = numeroDeItens;
        this.dataDeValidade = converterData(dataDeValidade);
    }

    private Date converterData(String datastr) throws ParseException {
    	SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		return sdf.parse(datastr);
    }
    
    private String formatarData(Date data) {
    	SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
    	return sdf.format(data);
    }
    
    public long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getNumeroDeItens() {
        return numeroDeItens;
    }

    public void setNumeroDeItens(int numeroDeItens) {
        this.numeroDeItens = numeroDeItens;
    }

    public void adicionaItens(int qtd) {
        this.numeroDeItens += qtd;
    }

    public Date getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(String dataDeValidade) throws ParseException {
        this.dataDeValidade = converterData(dataDeValidade);
    }
    
    public boolean isLoteVencido() throws ParseException {
		Date diasAtuais = new Date();
		return this.dataDeValidade.before(diasAtuais);
	}

    public boolean isLoteProximoDoVencimento() throws ParseException {
        Date diasAtuais = new Date();

        long diferencaEmMs = this.dataDeValidade.getTime() - diasAtuais.getTime();
        long diferencaEmDias = TimeUnit.DAYS.convert(diferencaEmMs, TimeUnit.MILLISECONDS);

        return diferencaEmDias <= 30 && diferencaEmDias >= 0;
    }



    @Override
    public String toString() {
        return "Lote{" +
                "id=" + id +
                ", produto=" + produto.getId() +
                ", numeroDeItens=" + numeroDeItens +
                ", dataDeValidade='" + this.formatarData(this.dataDeValidade) + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lote lote = (Lote) o;
        return id == lote.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
