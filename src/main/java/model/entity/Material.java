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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SequenceGenerator(name = "materialSequence", sequenceName = "MATERIAL_ID_SEQ", allocationSize = 1)
@Table(name = "MATERIAL")
@Where(clause = "ativo = '1'")  
@SQLDelete(sql = "UPDATE sysaid_java.MATERIAL SET ativo  = 0 WHERE id = ?")

public class Material extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Material() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "materialSequence")
	@Column
	private int id;		
	private String material;
	private String descricao;
	private int ativo = 1;
	
	@ManyToOne
	@JoinColumn(name="id_unidadeMedida", referencedColumnName="id")
	private UnidadeMedida unidadeMedida;
	@Column(length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal estoqueInformado;
	@Column(length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal estoqueCalculado;
	@ManyToOne
	@JoinColumn(name="id_tipomaterial", referencedColumnName="id")
	private TipoMaterial tipoMaterial;
	@ManyToOne
	@JoinColumn(name="id_aplicacao", referencedColumnName="id")
	private Aplicacao aplicacao;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public BigDecimal getEstoqueInformado() {
		if (estoqueInformado != null) {
			return this.estoqueInformado.setScale(2,BigDecimal.ROUND_DOWN) ;
		}
		return this.estoqueInformado;
	}

	public void setEstoqueInformado(BigDecimal estoqueInformado) {
		this.estoqueInformado = estoqueInformado;
	}

	public BigDecimal getEstoqueCalculado() {
		if (estoqueCalculado != null) {
			return this.estoqueCalculado.setScale(2,BigDecimal.ROUND_DOWN) ;
		}
		return this.estoqueCalculado;
	}
	
	public void setEstoqueCalculado(BigDecimal estoqueCalculado) {
		this.estoqueCalculado = estoqueCalculado;
	}

	public TipoMaterial getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(TipoMaterial tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public Aplicacao getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(Aplicacao aplicacao) {
		this.aplicacao = aplicacao;
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
		if (obj instanceof Material) {
			Material aplicacao = (Material) obj;
			return aplicacao.getId() == id;
		}

		return false;
	}
	
}