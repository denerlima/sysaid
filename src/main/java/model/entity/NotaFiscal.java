package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SequenceGenerator(name = "notaFiscalSequence", sequenceName = "NOTAFISCAL_ID_SEQ", allocationSize = 1)
@Table(name = "MF_NOTAFISCAL")
@Where(clause = "ativo = '1'")  
@SQLDelete(sql = "UPDATE sysaid_java.MF_NOTAFISCAL SET ativo  = 0 WHERE id = ?")

public class NotaFiscal extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public NotaFiscal() {
		super();
		this.notas = new ArrayList<Material>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notaFiscalSequence")
	@Column
	private Integer id;	
	private int ativo = 1;
	private Long nrNotaFiscal;
	private Date dataEmissao;
	private Date dataEntrega;	
	private Long acrescimos;
	private Long totalProdutos;
	private Long totalGeralNota;
	
	@ManyToOne
	@JoinColumn(name="id_fornecedor", referencedColumnName="id")
	private Fornecedor fornecedor;
	
	@ManyToMany
	private List<Material> notas;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
			
	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	public Long getNrNotaFiscal() {
		return nrNotaFiscal;
	}

	public void setNrNotaFiscal(Long nrNotaFiscal) {
		this.nrNotaFiscal = nrNotaFiscal;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Long getAcrescimos() {
		return acrescimos;
	}

	public void setAcrescimos(Long acrescimos) {
		this.acrescimos = acrescimos;
	}

	public Long getTotalProdutos() {
		return totalProdutos;
	}

	public void setTotalProdutos(Long totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public Long getTotalGeralNota() {
		return totalGeralNota;
	}

	public void setTotalGeralNota(Long totalGeralNota) {
		this.totalGeralNota = totalGeralNota;
	}

	public List<Material> getNotas() {
		return notas;
	}

	public void setNotas(List<Material> notas) {
		this.notas = notas;
	}

	public List<Material> getMateriais() {
		return notas;
	}

	public void setMateriais(List<Material> notas) {
		this.notas = notas;
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
		if (obj instanceof NotaFiscal) {
			NotaFiscal notaFiscal = (NotaFiscal) obj;
			return notaFiscal.getId() == id;
		}

		return false;
	}
	

}