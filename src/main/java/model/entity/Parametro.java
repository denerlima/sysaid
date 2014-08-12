package model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MF_PARAMETRO")
public class Parametro extends GenericModelo implements Serializable {
	private static final long serialVersionUID = 1L;

	public Parametro() {
		super();
	}

	@Id	
	@Column
	private Integer id = 1 ;
	private Integer qtdMeses = 0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQtdMeses() {
		return qtdMeses;
	}

	public void setQtdMeses(Integer qtdMeses) {
		this.qtdMeses = qtdMeses;
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
		Parametro other = (Parametro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}