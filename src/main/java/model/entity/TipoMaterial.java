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

@Entity
@SequenceGenerator(name = "tipoMaterialSequence", sequenceName = "TIPOMATERIAL_ID_SEQ", allocationSize = 1)
@Table(name = "TIPOMATERIAL")
public class TipoMaterial extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public TipoMaterial() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoMaterialSequence")
	@Column
	private int id;	
	private String descricao;
	
	@OneToMany(mappedBy="tipoMaterial")
	private List<Material> listaMaterial;

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


	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TipoMaterial) {
			TipoMaterial tipoMaterial = (TipoMaterial) obj;
			return tipoMaterial.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
}