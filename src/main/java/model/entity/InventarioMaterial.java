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

@Entity
@SequenceGenerator(name = "inventarioMaterialSequence", sequenceName = "INVENTARIO_MATERIAL_ID_SEQ", allocationSize = 1)
@Table(name = "MF_INVENTARIO_MF_MATERIAL")  
public class InventarioMaterial implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventarioMaterialSequence")
	@Column
	private Integer id;	
	
	@Column
	private int ativo = 1;
	
	@ManyToOne
	@JoinColumn(name="id_inventario", referencedColumnName="id")
	private Inventario inventario;
	
	@ManyToOne
	@JoinColumn(name="id_material", referencedColumnName="id")
	private Material material;
	
	@Column(name="quantidade_estoque")
	private BigDecimal quantidadeEstoque;
	
	@Column(name="quantidade_inventariada")
	private BigDecimal quantidadeInventariada;

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

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public BigDecimal getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(BigDecimal quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public BigDecimal getQuantidadeInventariada() {
		return quantidadeInventariada;
	}

	public void setQuantidadeInventariada(BigDecimal quantidadeInventariada) {
		this.quantidadeInventariada = quantidadeInventariada;
	}
	
}
