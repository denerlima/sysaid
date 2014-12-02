package model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
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
@SequenceGenerator(name = "unidadeMedidaSaidaSequence", sequenceName = "MF_UNI_MED_SAIDA_ID_SEQ", allocationSize = 1)
@Table(name = "MF_UNIDADEMEDIDA_SAIDA")
public class UnidadeMedidaSaida extends GenericModelo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public UnidadeMedidaSaida() {
		super();
	}
	
	public UnidadeMedidaSaida(Unidade unidade) {
		super();
		setUnidade(unidade);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidadeMedidaSaidaSequence")
	@Column
	private Integer id;	
	
	@Column
	private int ativo = 1;
	
	@Column(nullable = false)
	private BigDecimal fatorConversao;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="id_unidade", referencedColumnName="id")
	private Unidade unidade;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade_medida", referencedColumnName="id")
	private UnidadeMedida unidadeMedida;

	/*
	 * Métodos Getters and Setters
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

	public BigDecimal getFatorConversao() {
		return fatorConversao;
	}

	public void setFatorConversao(BigDecimal fatorConversao) {
		this.fatorConversao = fatorConversao;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	
	public BigDecimal getQuantidadeConvertida(BigDecimal quantidade) {
		return quantidade.multiply(getFatorConversao());
	}
	
	public BigDecimal getQuantidadeDesconvertida(BigDecimal quantidade) {
		return quantidade.divide(getFatorConversao());
	}
	
}
