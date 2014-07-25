package model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "notaFiscalMaterialSequence", sequenceName = "NOTAFISCAL_MATERIAL_ID_SEQ", allocationSize = 1)
@Table(name = "MF_NOTAFISCAL_MF_MATERIAL")
public class NotaFiscalMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notaFiscalMaterialSequence")
	@Column
	private Integer id;	
	
	@Column
	private int ativo = 1;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_notafiscal", referencedColumnName="id")
	private NotaFiscal notaFiscal;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_ordem_compra_material", referencedColumnName="id")
	private OrdemDeCompraMaterial ordemDeCompraMaterial;
	
	@Column(name="quantidade", length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal quantidade = new BigDecimal(0);
	
	@Column(name="preco_unitario", length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal precoUnitario = new BigDecimal(0);
	
	@Column(name="percentual_desconto", length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal percentualDesconto = new BigDecimal(0);
	
	@Column(name="valor_desconto", length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal valorDesconto = new BigDecimal(0);
	
	@Column(name="total", length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal total = new BigDecimal(0);

	/*
	 * Getters and Setters
	 */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public OrdemDeCompraMaterial getOrdemDeCompraMaterial() {
		return ordemDeCompraMaterial;
	}

	public void setOrdemDeCompraMaterial(OrdemDeCompraMaterial ordemDeCompraMaterial) {
		this.ordemDeCompraMaterial = ordemDeCompraMaterial;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
}
