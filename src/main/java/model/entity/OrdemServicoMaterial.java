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
@SequenceGenerator(name = "ordemServicoMaterialSequence", sequenceName = "MF_ORDEMSERVICO_MAT_ID_SEQ", allocationSize = 1)
@Table(name = "MF_ORDEMSERVICO_MF_MATERIAL")
public class OrdemServicoMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordemServicoMaterialSequence")
	@Column
	private Integer id;	
	
	@Column
	private int ativo = 1;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_ordem_servico", referencedColumnName="id")
	private OrdemServico ordemServico;
	
	@ManyToOne
	@JoinColumn(name="id_material", referencedColumnName="id")
	private Material material;
	
	@ManyToOne
	@JoinColumn(name="id_uni_med_saida", referencedColumnName="id")
	private UnidadeMedidaSaida unidadeMedidaSaida;
	
	@Column(name="quantidade_solicitada", nullable = false)
	private BigDecimal quantidadeSolicitada = new BigDecimal(0);
	
	@Column(name="quantidade_entregue", nullable = false)
	private BigDecimal quantidadeEntregue = new BigDecimal(0);
	
	@Column(name="quantidade_pendente", nullable = false)
	private BigDecimal quantidadePendente = new BigDecimal(0);
	
	

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

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public BigDecimal getQuantidadeSolicitada() {
		return quantidadeSolicitada;
	}

	public void setQuantidadeSolicitada(BigDecimal quantidadeSolicitada) {
		this.quantidadeSolicitada = quantidadeSolicitada;
	}

	public BigDecimal getQuantidadeEntregue() {
		return quantidadeEntregue;
	}

	public void setQuantidadeEntregue(BigDecimal quantidadeEntregue) {
		this.quantidadeEntregue = quantidadeEntregue;
	}

	public BigDecimal getQuantidadePendente() {
		return quantidadePendente;
	}

	public void setQuantidadePendente(BigDecimal quantidadePendente) {
		this.quantidadePendente = quantidadePendente;
	}

	public UnidadeMedidaSaida getUnidadeMedidaSaida() {
		return unidadeMedidaSaida;
	}

	public void setUnidadeMedidaSaida(UnidadeMedidaSaida unidadeMedidaSaida) {
		this.unidadeMedidaSaida = unidadeMedidaSaida;
	}

}
