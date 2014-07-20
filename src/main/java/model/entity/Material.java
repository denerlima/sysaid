package model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@SequenceGenerator(name = "materialSequence", sequenceName = "MATERIAL_ID_SEQ", allocationSize = 1)
@NamedQuery(name = "Material.findMaterialByNomeMaterial", query = "select m from Material m where m.material LIKE :material")
@Table(name = "MF_MATERIAL")
@Where(clause = "ativo = '1'")  
@SQLDelete(sql = "UPDATE sysaid_java.MF_MATERIAL SET ativo  = 0 WHERE id = ?")

public class Material extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_NOME_MATERIAL = "Material.findMaterialByNomeMaterial";
	
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
	@ManyToOne
	@JoinColumn(name="id_grupo", referencedColumnName="id")
	private Grupo grupo;
	@ManyToOne
	@JoinColumn(name="id_marca", referencedColumnName="id")
	private Marca marca;
	
	@OneToMany(mappedBy="material")
	private List<OrdemDeCompraMaterial> ordensDeCompra;

	@OneToMany(mappedBy="material")
	private List<InventarioMaterial> inventarios;
	
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
	
	@Override
	public String toString() {
		return material;
	}
	
}