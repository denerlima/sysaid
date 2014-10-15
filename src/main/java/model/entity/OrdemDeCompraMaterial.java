package model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@SequenceGenerator(name = "ordemDeCompraMaterialSequence", sequenceName = "MF_ORDEMDECOMPRA_MAT_ID_SEQ", allocationSize = 1)
@Table(name = "MF_ORDEMDECOMPRA_MF_MATERIAL")
public class OrdemDeCompraMaterial implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordemDeCompraMaterialSequence")
	@Column
	private Integer id;	
	
	@Column
	private int ativo = 1;
	
	@ManyToOne
	@JoinColumn(name="id_ordemdecompra", referencedColumnName="id")
	private OrdemDeCompra ordemDeCompra;
	
	@ManyToOne
	@JoinColumn(name="id_material", referencedColumnName="id")
	private Material material;
	
	@Column(name="quantidade_autorizada")
	private BigDecimal quantidadeAutorizada;

	@Transient
	private boolean selecionado;
	
	@Transient
	private BigDecimal quantidadeRecebida;
	
	@Transient
	private BigDecimal quantidadePendente;
	
	
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

	public OrdemDeCompra getOrdemDeCompra() {
		return ordemDeCompra;
	}

	public void setOrdemDeCompra(OrdemDeCompra ordemDeCompra) {
		this.ordemDeCompra = ordemDeCompra;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public BigDecimal getQuantidadeAutorizada() {
		return quantidadeAutorizada;
	}

	public void setQuantidadeAutorizada(BigDecimal quantidadeAutorizada) {
		this.quantidadeAutorizada = quantidadeAutorizada;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public BigDecimal getQuantidadeRecebida() {
		return quantidadeRecebida;
	}

	public void setQuantidadeRecebida(BigDecimal quantidadeRecebida) {
		this.quantidadeRecebida = quantidadeRecebida;
	}

	public BigDecimal getQuantidadePendente() {
		return quantidadePendente;
	}

	public void setQuantidadePendente(BigDecimal quantidadePendente) {
		this.quantidadePendente = quantidadePendente;
	}
	
}
