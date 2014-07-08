package model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SequenceGenerator(name = "inventarioSequence", sequenceName = "INVENTARIO_ID_SEQ", allocationSize = 1)
@Table(name = "INVENTARIO")
@Where(clause = "ativo = '1'")  
@SQLDelete(sql = "UPDATE sysaid_java.INVENTARIO SET ativo  = 0 WHERE id = ?")

public class Inventario extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Inventario() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventarioSequence")
	@Column
	private int id;	
	private int ativo = 1;
	private Long numeroInventario;
	private Date dataInventario;
	private String atendente;
	private String justificativa;
	
	
	@ManyToMany
	private List<Material> materiais;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getAtendente() {
		return atendente;
	}

	public void setAtendente(String atendente) {
		this.atendente = atendente;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public List<Material> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<Material> materiais) {
		this.materiais = materiais;
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
		if (obj instanceof Inventario) {
			Inventario inventario = (Inventario) obj;
			return inventario.getId() == id;
		}

		return false;
	}
	

}