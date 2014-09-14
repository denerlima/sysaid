package model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@SequenceGenerator(name = "materialSequence", sequenceName = "MF_MATERIAL_ID_SEQ", allocationSize = 1)
@NamedQuery(name = "Material.findMaterialByNomeMaterial", query = "select m from Material m where m.material LIKE :material")
@Table(name = "MF_MATERIAL")
public class Material extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_NOME_MATERIAL = "Material.findMaterialByNomeMaterial";
	
	public Material() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "materialSequence")
	@Column
	private Integer id;
	
	@Column
	private String material;
	
	@Column
	private String descricao;
	
	@Column
	private int ativo = 1;
	
	@Column
	private String codigoBarra;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidadeMedida", referencedColumnName="id")
	private UnidadeMedida unidadeMedida;
	
	@Column(nullable = false)
	private BigDecimal estoque;
	
	@Column(nullable = false)
	private BigDecimal estoqueInformado;
	
	@Column(nullable = false)
	private BigDecimal estoqueCalculado;
	
	@Column(nullable = false)
	private BigDecimal ajuste;
	
	@Column(nullable = false)
	private BigDecimal qtdSolicitada;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipomaterial", referencedColumnName="id")
	private TipoMaterial tipoMaterial;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_aplicacao", referencedColumnName="id")
	private Aplicacao aplicacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_grupo", referencedColumnName="id")
	private Grupo grupo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_marca", referencedColumnName="id")
	private Marca marca;
	
	@Column(name="preco_unitario", length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal precoUnitario = new BigDecimal(0);
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="material")
	private List<OrdemDeCompraMaterial> ordensDeCompra;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="material")
	private List<InventarioMaterial> inventarios;
	
	@Transient
	private BigDecimal sugestaoCompra;
	
	/*
	 * Getters and Setters
	 */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		return this.estoqueInformado;
	}

	public void setEstoqueInformado(BigDecimal estoqueInformado) {
		this.estoqueInformado = estoqueInformado;
	}

	public BigDecimal getEstoqueCalculado() {
		return this.estoqueCalculado;
	}
	
	public void setEstoqueCalculado(BigDecimal estoqueCalculado) {
		this.estoqueCalculado = estoqueCalculado;
	}
	
	public BigDecimal getAjuste() {
		return ajuste;
	}

	public void setAjuste(BigDecimal ajuste) {
		this.ajuste = ajuste;
	}
	
	public BigDecimal getQtdSolicitada() {
		return qtdSolicitada;
	}

	public void setQtdSolicitada(BigDecimal qtdSolicitada) {
		this.qtdSolicitada = qtdSolicitada;
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
		
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public List<OrdemDeCompraMaterial> getOrdensDeCompra() {
		return ordensDeCompra;
	}

	public void setOrdensDeCompra(List<OrdemDeCompraMaterial> ordensDeCompra) {
		this.ordensDeCompra = ordensDeCompra;
	}

	public List<InventarioMaterial> getInventarios() {
		return inventarios;
	}

	public void setInventarios(List<InventarioMaterial> inventarios) {
		this.inventarios = inventarios;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	
	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	
	public BigDecimal getEstoque() {
		return estoque;
	}

	public void setEstoque(BigDecimal estoque) {
		this.estoque = estoque;
	}
	
	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal valorUnitario) {
		this.precoUnitario = valorUnitario;
	}

	/**
	 * Fórmula do campo SUGESTÃO DE COMPRA: SUGESTÃO DE COMPRA = [ESTOQUE MINIMO] - ESTOQUE - [QUANTIDADE SOLICITADA] + [PENDENCIAS]
	 * @return BigDecimal
	 */
	
	public BigDecimal getSugestaoCompra() {		
		if (sugestaoCompra == null) {
			if (getMaterial() != null){
				BigDecimal estoqueMinimo = new BigDecimal(0);
				if (getEstoqueInformado() != null) {
					estoqueMinimo = getEstoqueInformado();
				}else {
					estoqueMinimo = (getEstoqueCalculado().multiply(getAjuste()));
				}				
				return (estoqueMinimo.subtract(getEstoque()).subtract(getQtdSolicitada()));
								
			}
		}
		return new BigDecimal(0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return material;
	}
	
	public String getMaterialLabel() {
		if(getMarca() != null) {
			return getMaterial() + " - " + getMarca().getDescricao();
		} 
		return getMaterial();
	}
	
}
