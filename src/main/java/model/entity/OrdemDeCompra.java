package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@SequenceGenerator(name = "ordemDeCompraSequence", sequenceName = "ORDEMDECOMPRA_ID_SEQ", allocationSize = 1)
@Table(name = "MF_ORDEMDECOMPRA")
@Where(clause = "ativo = '1'")  
public class OrdemDeCompra extends GenericModelo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public OrdemDeCompra() {
		super();
		this.materiais = new ArrayList<OrdemDeCompraMaterial>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordemDeCompraSequence")
	@Column
	private Integer id;	
	private int ativo = 1;
	private Long numeroOC;
	private Date dataEmissao;
	private Date dataAutorizacao;
	private String autorizador;
	private String contratado;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ordemDeCompra", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrdemDeCompraMaterial> materiais;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
			
	public Long getNumeroOC() {
		return numeroOC;
	}

	public void setNumeroOC(Long numeroOC) {
		this.numeroOC = numeroOC;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(Date dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public String getAutorizador() {
		return autorizador;
	}

	public void setAutorizador(String autorizador) {
		this.autorizador = autorizador;
	}

	public String getContratado() {
		return contratado;
	}

	public void setContratado(String contratado) {
		this.contratado = contratado;
	}

	public List<OrdemDeCompraMaterial> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<OrdemDeCompraMaterial> materiais) {
		this.materiais = materiais;
	}
	
	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OrdemDeCompra) {
			OrdemDeCompra ordemDeCompra = (OrdemDeCompra) obj;
			return ordemDeCompra.getId() == id;
		}

		return false;
	}
	

}