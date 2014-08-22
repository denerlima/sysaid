package model.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

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
	
	@Column(name="quantidade_devolvida", nullable = false)
	private BigDecimal quantidadeDevolvida = new BigDecimal(0);
	
	@Column(name="quantidade_utilizada", nullable = false)
	private BigDecimal quantidadeUtilizada = new BigDecimal(0);
	
	@Column(name="preco_unitario", length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal precoUnitario = new BigDecimal(0);
	
	@OneToMany(mappedBy="ordemServicoMaterial", cascade=CascadeType.ALL)
	@Where(clause="tipo = 0")
	private Set<OrdemServicoMaterialHistorico> entregas = new HashSet<OrdemServicoMaterialHistorico>();
	
	@OneToMany(mappedBy="ordemServicoMaterial", cascade=CascadeType.ALL)
	@Where(clause="tipo = 1")
	private Set<OrdemServicoMaterialHistorico> baixasPendencias = new HashSet<OrdemServicoMaterialHistorico>();

	@OneToMany(mappedBy="ordemServicoMaterial", cascade=CascadeType.ALL)
	@Where(clause="tipo = 2")
	private Set<OrdemServicoMaterialHistorico> devolucoes = new HashSet<OrdemServicoMaterialHistorico>();
	
	@OneToMany(mappedBy="ordemServicoMaterial", cascade=CascadeType.ALL)
	@Where(clause="tipo = 3")
	private Set<OrdemServicoMaterialHistorico> realizados = new HashSet<OrdemServicoMaterialHistorico>();
	
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

	public BigDecimal getQuantidadeDevolvida() {
		return quantidadeDevolvida;
	}

	public void setQuantidadeDevolvida(BigDecimal quantidadeDevolvida) {
		this.quantidadeDevolvida = quantidadeDevolvida;
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

	public BigDecimal getQuantidadeUtilizada() {
		return quantidadeUtilizada;
	}

	public void setQuantidadeUtilizada(BigDecimal quantidadeUtilizada) {
		this.quantidadeUtilizada = quantidadeUtilizada;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public UnidadeMedidaSaida getUnidadeMedidaSaida() {
		return unidadeMedidaSaida;
	}

	public void setUnidadeMedidaSaida(UnidadeMedidaSaida unidadeMedidaSaida) {
		this.unidadeMedidaSaida = unidadeMedidaSaida;
	}

	public Set<OrdemServicoMaterialHistorico> getBaixasPendencias() {
		return baixasPendencias;
	}

	public void setBaixasPendencias(Set<OrdemServicoMaterialHistorico> baixasPendencias) {
		this.baixasPendencias = baixasPendencias;
	}

	public Set<OrdemServicoMaterialHistorico> getDevolucoes() {
		return devolucoes;
	}

	public void setDevolucoes(Set<OrdemServicoMaterialHistorico> devolucoes) {
		this.devolucoes = devolucoes;
	}

	public Set<OrdemServicoMaterialHistorico> getRealizados() {
		return realizados;
	}

	public void setRealizados(Set<OrdemServicoMaterialHistorico> realizados) {
		this.realizados = realizados;
	}

	public Set<OrdemServicoMaterialHistorico> getEntregas() {
		return entregas;
	}

	public void setEntregas(Set<OrdemServicoMaterialHistorico> entregas) {
		this.entregas = entregas;
	}

	public BigDecimal getQuantidadeRetirada() {
		return getQuantidadeEntregue().subtract(getQuantidadeDevolvida());
	}
	
	public BigDecimal getPrecoTotal() {
		return getPrecoUnitario().multiply(getQuantidadeUtilizada());
	}
	
}
