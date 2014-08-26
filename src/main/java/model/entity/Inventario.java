package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

@Entity
@SequenceGenerator(name = "inventarioSequence", sequenceName = "MF_INVENTARIO_ID_SEQ", allocationSize = 1)
@Table(name = "MF_INVENTARIO")
public class Inventario extends GenericModelo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final int STATUS_CONCLUIDO = 1;
	
	public Inventario() {
		super();
		this.materiais = new ArrayList<InventarioMaterial>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventarioSequence")
	@Column
	private Integer id;	
	private int ativo = 1;
	private Long numeroInventario;
	private Date dataInventario;
	
	@Column(length=1000)
	private String justificativa;
	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="inventario", cascade=CascadeType.ALL)
	private List<InventarioMaterial> materiais;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_name_atendente", referencedColumnName="user_name")
	private Usuario atendente;
	
	@Column
	private Integer status;
	
	/*
	 * Métodos Getters and Setters
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
			
	public Long getNumeroInventario() {
		return numeroInventario;
	}

	public void setNumeroInventario(Long numeroInventario) {
		this.numeroInventario = numeroInventario;
	}

	public Date getDataInventario() {
		return dataInventario;
	}

	public void setDataInventario(Date dataInventario) {
		this.dataInventario = dataInventario;
	}

	public Usuario getAtendente() {
		return atendente;
	}

	public void setAtendente(Usuario atendente) {
		this.atendente = atendente;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public List<InventarioMaterial> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<InventarioMaterial> materiais) {
		this.materiais = materiais;
	}
	
	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		Inventario other = (Inventario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean isStatusNull() {
		return status == null;
	}
	
	public String getStatusLabel() {
		if(status != null && status == 1) {
			return "Conclu’do";
		}
		return "N‹o Conclu’do";
	}
}