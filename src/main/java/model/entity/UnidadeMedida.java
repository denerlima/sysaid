package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
@SequenceGenerator(name = "unidadeMedidaSequence", sequenceName = "MF_UNIDADEMEDIDA_ID_SEQ", allocationSize = 1)
@Table(name = "MF_UNIDADEMEDIDA")
public class UnidadeMedida extends GenericModelo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidadeMedidaSequence")
	@Column
	private Integer id;	
	
	@Column
	private int ativo = 1;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="id_unidadeentrada", referencedColumnName="id")
	private Unidade unidadeEntrada;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="unidadeMedida", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<UnidadeMedidaSaida> saidas = new ArrayList<UnidadeMedidaSaida>();

	
	/*
	 * M�todos Getters and Setters
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

	public Unidade getUnidadeEntrada() {
		return unidadeEntrada;
	}

	public void setUnidadeEntrada(Unidade unidadeEntrada) {
		this.unidadeEntrada = unidadeEntrada;
	}

	public List<UnidadeMedidaSaida> getSaidas() {
		return saidas;
	}

	public void setSaidas(List<UnidadeMedidaSaida> saidas) {
		this.saidas = saidas;
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
		UnidadeMedida other = (UnidadeMedida) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
