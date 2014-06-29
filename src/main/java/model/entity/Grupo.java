package model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SequenceGenerator(name = "grupoSequence", sequenceName = "GRUPO_ID_SEQ", allocationSize = 1)
@Table(name = "GRUPO")
@Where(clause = "ativo = '1'")  
@SQLDelete(sql = "UPDATE sysaid_java.GRUPO SET ativo  = 0 WHERE id = ?")

public class Grupo extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Grupo() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grupoSequence")
	@Column
	private int id;	
	private String descricao;
	private String grupopai;
	private int ativo = 1;
	
	@OneToMany(mappedBy="grupo")
	private List<Material> grupo;

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
		
	public List<Material> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<Material> grupo) {
		this.grupo = grupo;
	}
	
	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	
	public String getGrupopai() {
		return grupopai;
	}

	public void setGrupopai(String grupopai) {
		this.grupopai = grupopai;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Grupo) {
			Grupo grupo = (Grupo) obj;
			return grupo.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
}