package model.entity;

import java.io.Serializable;
import java.util.List;

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
@SequenceGenerator(name = "aplicacaoSequence", sequenceName = "MF_APLICACAO_ID_SEQ", allocationSize = 1)
@Table(name = "MF_APLICACAO")
public class Aplicacao extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Aplicacao() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aplicacaoSequence")
	@Column
	private Integer id;
	
	@Column
	private String descricao;
	
	@Column
	private int ativo = 1;
	
	@OneToMany(mappedBy="aplicacao")
	private List<Material> aplicacao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_name_responsavel", referencedColumnName="user_name")
	private Usuario responsavel;
	
	/*
	 * Métodos Getters and Setters
	 */
	
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
		
	public List<Material> getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(List<Material> aplicacao) {
		this.aplicacao = aplicacao;
	}
	
	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
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
		Aplicacao other = (Aplicacao) obj;
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
}