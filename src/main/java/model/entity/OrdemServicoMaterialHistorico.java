package model.entity;

import java.math.BigDecimal;
import java.util.Date;

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
@SequenceGenerator(name = "ordemServicoMaterialHistoricoSequence", sequenceName = "MF_OS_MAT_HIST_ID_SEQ", allocationSize = 1)
@Table(name = "MF_OS_MF_MATERIAL_HIST")
public class OrdemServicoMaterialHistorico {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordemServicoMaterialHistoricoSequence")
	@Column
	private Integer id;	
	
	@Column
	private int ativo = 1;
	
	@Column(nullable=false)
	private Date data;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_ordem_serv_material", referencedColumnName="id", nullable=false)
	private OrdemServicoMaterial ordemServicoMaterial;
	
	@Column
	private int tipo;
	
	@Column(name="quantidade", nullable=false)
	private BigDecimal quantidade = new BigDecimal(0);
	
	@Column(name="quantidade_anterior", nullable=false)
	private BigDecimal quantidadeAnterior = new BigDecimal(0);	
	
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public OrdemServicoMaterial getOrdemServicoMaterial() {
		return ordemServicoMaterial;
	}

	public void setOrdemServicoMaterial(OrdemServicoMaterial ordemServicoMaterial) {
		this.ordemServicoMaterial = ordemServicoMaterial;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getQuantidadeAnterior() {
		return quantidadeAnterior;
	}

	public void setQuantidadeAnterior(BigDecimal quantidadeAnterior) {
		this.quantidadeAnterior = quantidadeAnterior;
	}

}
