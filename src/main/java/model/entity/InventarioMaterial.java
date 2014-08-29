package model.entity;

import java.io.Serializable;
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
import javax.persistence.Transient;

@Entity
@SequenceGenerator(name = "inventarioMaterialSequence", sequenceName = "MF_INVENTARIO_MATERIAL_ID_SEQ", allocationSize = 1)
@Table(name = "MF_INVENTARIO_MF_MATERIAL")  
public class InventarioMaterial implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int STATUS_REJEITADO = 0;
	public static final int STATUS_APROVADO = 1;
	

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
	private BigDecimal quantidadeEstoque = new BigDecimal(0);
	
	@Column(name="quantidade_inventariada")
	private BigDecimal quantidadeInventariada = new BigDecimal(0);
	
	@Column(name="justificativa_inventariante", length=1000)
	private String justificativaInventariante;
	
	@Column(length=1000)
	private String justificativa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_name_usuario", referencedColumnName="user_name")
	private Usuario usuario;
	
	@Column
	private Integer status;
	
	@Transient
	private boolean selecionado;
	
	/*
	 * Métodos Getters/Setters
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

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	
	/*
	 * Regras
	 */
	
	public boolean isManaged() {
		return id != null;
	}
	
	public String getStatusLabel() {
		if(status == null) {
			return ""; 
		} else if(status == 0) {
			return "Rejeitado";
		}
		else if(status == 1) {
			return "Aprovado";
		}
		return null;
	}
	
	public boolean isAprovado() {
		return status != null && status == STATUS_APROVADO;
	}
	
	public boolean isRejeitado() {
		return status != null && status == STATUS_REJEITADO;
	}
	
	public boolean isStatusNull() {
		return status == null;
	}

	public BigDecimal getDiferenca() {
		return getQuantidadeEstoque().subtract(getQuantidadeInventariada());
	}
	
}
