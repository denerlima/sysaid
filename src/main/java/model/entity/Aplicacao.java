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
@SequenceGenerator(name = "aplicacaoSequence", sequenceName = "APLICACAO_ID_SEQ", allocationSize = 1)
@Table(name = "APLICACAO")
public class Aplicacao extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Aplicacao() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aplicacaoSequence")
	@Column
	private int id;	
	private String descricao;
	
	@OneToMany(mappedBy="unidadeMedida")
	private List<Material> aplicacao;

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
		
	public List<Material> getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(List<Material> aplicacao) {
		this.aplicacao = aplicacao;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Aplicacao) {
			Aplicacao aplicacao = (Aplicacao) obj;
			return aplicacao.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
}