package model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@SequenceGenerator(name = "grupoSequence", sequenceName = "MF_GRUPO_ID_SEQ", allocationSize = 1)
@Table(name = "MF_GRUPO")
public class Grupo extends GenericModelo implements Serializable{
	
	private static final long serialVersionUID = 3305296226510800199L;

	public Grupo() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grupoSequence")
	@Column
	private Integer id;	
	
	@Column
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="id_grupo_pai", referencedColumnName="id")
	private Grupo grupoPai;
	
	private int ativo = 1;
	
	@OneToMany(mappedBy="grupo")
	private List<Material> materiais;

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
		
	public List<Material> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<Material> grupo) {
		this.materiais = grupo;
	}
	
	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	
	public Grupo getGrupoPai() {
		return grupoPai;
	}

	public void setGrupoPai(Grupo grupoPai) {
		this.grupoPai = grupoPai;
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
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return descricao;
	}
	
	public String getDescricaoLabel() {
		if(getGrupoPai() != null) {
			return getDescricao() + " - " + getGrupoPai();
		}
		return getDescricao();
	}
	
	@Override
	public String toJson() {
		JSONObject j = new JSONObject();
		j.put("id", getId());
		j.put("descricao", getDescricao());
		if(getGrupoPai() != null) {
			j.put("grupoPai", getGrupoPai().getId());
		}
		return "";
	}
	
}
