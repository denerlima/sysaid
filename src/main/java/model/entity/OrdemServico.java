package model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "MF_ORDEMSERVICO")
public class OrdemServico extends GenericModelo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private Integer id;	
	
	@Column
	private int ativo = 1;
	
	@Column
	private String justificativa;
	
	@OneToMany(mappedBy="ordemServico", cascade=CascadeType.ALL)
	@OrderBy("data desc")
	private Set<OrdemServicoMaterial> materiais = new HashSet<OrdemServicoMaterial>();
	
	@OneToMany(mappedBy="ordemServico", cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<OrdemServicoMaoDeObra> maosDeObras = new HashSet<OrdemServicoMaoDeObra>();
	
	@Transient
	private String solicitante;
	@Transient
	private BigDecimal ramal;
	@Transient
	private String areaSolicitante;
	@Transient
	private Date dataAbertura;
	@Transient
	private Date dataFechamento;
	@Transient
	private String enderecoAtendimento;
	
	
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

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Set<OrdemServicoMaterial> getMateriais() {
		return materiais;
	}

	public void setMateriais(Set<OrdemServicoMaterial> materiais) {
		this.materiais = materiais;
	}

	public Set<OrdemServicoMaoDeObra> getMaosDeObras() {
		return maosDeObras;
	}

	public void setMaosDeObras(Set<OrdemServicoMaoDeObra> maosDeObras) {
		this.maosDeObras = maosDeObras;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public BigDecimal getRamal() {
		return ramal;
	}

	public void setRamal(BigDecimal ramal) {
		this.ramal = ramal;
	}

	public String getAreaSolicitante() {
		return areaSolicitante;
	}

	public void setAreaSolicitante(String areaSolicitante) {
		this.areaSolicitante = areaSolicitante;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public String getEnderecoAtendimento() {
		return enderecoAtendimento;
	}

	public void setEnderecoAtendimento(String enderecoAtendimento) {
		this.enderecoAtendimento = enderecoAtendimento;
	}
	
}
